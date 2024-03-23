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

public class AbstractItemClickEvent extends AbstractItemActivationEvent implements Cancellable {


    private AbstractItemClickEvent(@NotNull AbstractItem clickedItem, @NotNull Player player, @NotNull Map<String, ActionPacket> packets) {
        super(clickedItem, player, packets);
    }

    public static AbstractItemClickEvent createInstance(AbstractItem item, Player player, Map<String, ActionPacket> packets, ItemActionEventFactory instance) {
        return new AbstractItemClickEvent(item,player,packets);
    }
}
