package mintychochip.genesis.commands.genesis;

import mintychochip.genesis.commands.abstraction.GenericCommandObject;
import mintychochip.genesis.commands.abstraction.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ItemInfo extends GenericCommandObject implements SubCommand {
    public ItemInfo(String executor, String description) {
        super(executor, description);
    }

    @Override
    public boolean execute(String[] strings, Player sender) {
        int length = strings.length;
        if(length < depth) {
            return false;
        }
        String hand = strings[length - 1];
        if(hand.equalsIgnoreCase("off") || hand.equalsIgnoreCase("main")) {
            PlayerInventory inventory = sender.getInventory();
            if(hand.equalsIgnoreCase("main")) {
                ItemStack itemInMainHand = inventory.getItemInMainHand();
                if(!handHoldingAir(itemInMainHand)) {
                    printItemInfoInHand(itemInMainHand,sender);
                }
            } else {
                ItemStack itemInOffHand = inventory.getItemInOffHand();
                if(!handHoldingAir(itemInOffHand)) {
                    printItemInfoInHand(itemInOffHand,sender);
                }
            }
        }
        return true;
    }

    private boolean handHoldingAir(@NotNull ItemStack itemStack) {
        return itemStack.getType() == Material.AIR;
    }
    private boolean printItemInfoInHand(@NotNull ItemStack itemStack, @NotNull Player sender) {
        Material type = itemStack.getType();
        int amount = itemStack.getAmount();
        ItemMeta itemMeta = itemStack.getItemMeta();
        String displayName = itemMeta.getDisplayName();
        int customModelData = itemMeta.getCustomModelData();
        boolean unbreakable = itemMeta.isUnbreakable();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        Set<NamespacedKey> keys = persistentDataContainer.getKeys();

        sender.sendMessage("Item Material: " + type);
        sender.sendMessage("Amount: " + amount);
        sender.sendMessage("Item Display Name: " + displayName);
        sender.sendMessage("Model: " + customModelData);
        sender.sendMessage("Unbreakable: " + unbreakable);
        sender.sendMessage("Keys: " + keys.toString());
        return true;
    }

    private void sendPlayerMessage(@NotNull String value, @NotNull Player sender) {
        sender.sendMessage(ChatColor.GRAY + value);
    }
}
