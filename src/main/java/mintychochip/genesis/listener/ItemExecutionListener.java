package mintychochip.genesis.listener;

import com.google.gson.Gson;
import mintychochip.genesis.Genesis;
import mintychochip.genesis.container.items.AbstractItem;
import mintychochip.genesis.container.items.ActionData;
import mintychochip.genesis.container.items.actions.ActionPacket;
import mintychochip.genesis.events.AbstractItemClickEvent;
import mintychochip.genesis.events.AbstractItemConsumeEvent;
import mintychochip.genesis.events.ActionEventType;
import mintychochip.genesis.events.ItemActionEventFactory;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.entity.Player;

import java.util.Map;

public class ItemExecutionListener implements Listener { //priority always equals monitor

    private final Gson gson = new Gson();
    private final ItemActionEventFactory factory;
    public ItemExecutionListener(ItemActionEventFactory factory) {
        this.factory = factory;
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
            ActionData actionData = gson.fromJson(s, ActionData.class);
            AbstractItem build = new AbstractItem.ItemBuilder(Genesis.getInstance(), item, false).build();
            Bukkit.getPluginManager().callEvent(factory.createInstance(build,event.getPlayer(),actionData.getPackets(), ActionEventType.CLICK));
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerConsumeItemEvent(final PlayerItemConsumeEvent event) {
        if(event.isCancelled()) {
            return;
        }
        ItemStack item = event.getItem();
        Bukkit.broadcastMessage(item.toString());
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) {
            return;
        }
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        if(persistentDataContainer.has(Genesis.getKey("consumable"),PersistentDataType.STRING)) {
            String s = itemMeta.getPersistentDataContainer().get(Genesis.getKey("consumable"), PersistentDataType.STRING);
            ActionData actionData = gson.fromJson(s, ActionData.class);
            AbstractItem build = new AbstractItem.ItemBuilder(Genesis.getInstance(), item, false).build();
            Bukkit.getPluginManager().callEvent(factory.createInstance(build,event.getPlayer(),actionData.getPackets(),ActionEventType.CONSUME));
        }
    }
    @EventHandler
    private void onAbstractItemConsumeEvent(final AbstractItemConsumeEvent event) {
        if(event.isCancelled()) {
            return;
        }
        Map<String, ActionPacket> packets = event.getPackets();
        if(packets.isEmpty()) {
            return;
        }
        handleActionEvent(event.getPlayer(),packets);
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
        handleActionEvent(event.getPlayer(),packets);
    }

    private void handleActionEvent(Player target, Map<String,ActionPacket> packets) {
        for (ActionPacket packet : packets.values()) {
            switch (packet.getEventAction()) {
                case SEND_MESSAGE -> {
                    target.sendMessage(packet.getValue());
                }
                case EXECUTE_COMMAND -> {
                    target.performCommand(packet.getValue());
                }
            }
        }
    }

}



