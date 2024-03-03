package mintychochip.genesis.events;

import mintychochip.genesis.container.items.AbstractItem;
import mintychochip.genesis.container.items.actions.ActionPacket;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class AbstractItemClickEvent extends Event implements Cancellable {

    private final AbstractItem item;
    private final Player clicker;
    private Map<String,ActionPacket> packets;

    private boolean cancelled;
    public AbstractItemClickEvent(AbstractItem item, Player clicker, Map<String,ActionPacket> packets) {
        this.item = item;
        this.clicker = clicker;
        this.packets = packets;
    }

    public Map<String, ActionPacket> getPackets() {
        return packets;
    }

    public AbstractItem getItem() {
        return item;
    }

    public Player getClicker() {
        return clicker;
    }

    private static final HandlerList handlers = new HandlerList();
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
}
