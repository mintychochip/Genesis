package mintychochip.genesis.listener;

import com.google.gson.Gson;
import mintychochip.genesis.Genesis;
import mintychochip.genesis.container.ClickableActionData;
import mintychochip.genesis.container.items.AbstractItem;
import mintychochip.genesis.container.items.actions.ActionPacket;
import mintychochip.genesis.container.items.interfaces.Embeddable;
import mintychochip.genesis.events.AbstractItemClickEvent;
import mintychochip.genesis.util.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ItemExecutionListener implements Listener { //priority always equals monitor

    private final Gson gson;

    public ItemExecutionListener(Gson gson) {
        this.gson = gson;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Check if the player right-clicked with the custom item
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return;
        }
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        if (persistentDataContainer.has(Genesis.getKey("clickable"), PersistentDataType.STRING)) {
            String s = itemMeta.getPersistentDataContainer().get(Genesis.getKey("clickable"), PersistentDataType.STRING);
            ClickableActionData clickableActionData = gson.fromJson(s, ClickableActionData.class);
            Map<String, ActionPacket> packets = clickableActionData.getPackets();
            Bukkit.getPluginManager().callEvent(new AbstractItemClickEvent(new AbstractItem.ItemBuilder(Genesis.getInstance(), item, false).build(), event.getPlayer(), packets));
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onAbstractItemEvent(final AbstractItemClickEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Map<String, ActionPacket> packets = event.getPackets();
        if (packets.isEmpty()) {
            return;
        }
        for (ActionPacket packet : packets.values()) {
            switch (packet.getEventAction()) {
                case SEND_MESSAGE -> {
                    event.getClicker().sendMessage(packet.getValue());
                }
                case EXECUTE_COMMAND -> {
                    event.getClicker().performCommand(packet.getValue());
                }
            }
        }
    }

}



