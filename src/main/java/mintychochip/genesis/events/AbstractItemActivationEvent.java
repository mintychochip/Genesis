package mintychochip.genesis.events;

import mintychochip.genesis.container.items.AbstractItem;
import mintychochip.genesis.container.items.actions.ActionPacket;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class AbstractItemActivationEvent extends AbstractEvent implements Cancellable {
    @NotNull
    protected final AbstractItem clickedItem;
    @NotNull
    protected final Player player;
    @NotNull
    protected final Map<String,ActionPacket> packets;
    protected boolean cancelled = false;
    protected AbstractItemActivationEvent(@NotNull AbstractItem clickedItem, @NotNull Player player, @NotNull Map<String, ActionPacket> packets) {
        this.clickedItem = clickedItem;
        this.player = player;
        this.packets = packets;
    }
    public @NotNull Map<String, ActionPacket> getPackets() {
        return packets;
    }

    public AbstractItem getItem() {
        return clickedItem;
    }

    public @NotNull Player getPlayer() {
        return player;
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
