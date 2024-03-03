package mintychochip.genesis.commands.genesis;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mintychochip.genesis.Genesis;
import mintychochip.genesis.commands.abstraction.GenericCommandObject;
import mintychochip.genesis.commands.abstraction.SubCommand;
import mintychochip.genesis.container.ClickableActionData;
import mintychochip.genesis.container.Trigger;
import mintychochip.genesis.container.items.actions.ActionPacket;
import mintychochip.genesis.container.items.actions.EventAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ExecuteCommandClickEvent extends GenericCommandObject implements SubCommand {

    private final Gson gson;

    public ExecuteCommandClickEvent(String executor, String description, Gson gson) {
        super(executor, description);
        this.gson = gson;
    }

    @Override
    public boolean execute(String[] strings, Player sender) {
        int length = strings.length;
        if (length < depth) {
            return false;
        }
        //genesis item add event {trigger} {EXECUTE_COMMAND key value}

        String key = strings[depth - 1];
        PlayerInventory inventory = sender.getInventory();
        ItemStack itemInMainHand = inventory.getItemInMainHand();
        if (key == null) {
            return false;
        }
        if (itemInMainHand.getType() == Material.AIR) {
            return false;
        }
        ItemMeta itemMeta = itemInMainHand.getItemMeta();
        if (itemMeta == null) {
            return false;
        }
        if (length >= depth + 1) {
            String json = strings[depth];
            ActionPacket actionPacket = gson.fromJson(json, ActionPacket.class);
            if(actionPacket == null) {
                return false;
            }
            PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

            ClickableActionData clickableActionData;

            if(!persistentDataContainer.has(Genesis.getKey("clickable"),PersistentDataType.STRING)) {
                clickableActionData = new ClickableActionData();
            } else {
                clickableActionData = gson.fromJson(persistentDataContainer.get(Genesis.getKey("clickable"),PersistentDataType.STRING), ClickableActionData.class);
            }
            clickableActionData.addActionPacket(key,actionPacket);

            Genesis.getSerializer().serializeToItem(clickableActionData,itemInMainHand);
        }
        return true;
    }
}
