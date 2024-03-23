package mintychochip.genesis.events;

import mintychochip.genesis.container.items.AbstractItem;
import mintychochip.genesis.container.items.actions.ActionPacket;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class AbstractItemConsumeEvent extends AbstractItemActivationEvent {
    private AbstractItemConsumeEvent(@NotNull AbstractItem clickedItem, @NotNull Player player, @NotNull Map<String, ActionPacket> packets) {
        super(clickedItem, player, packets);
    }

    public static AbstractItemConsumeEvent createInstance(AbstractItem clickedItem, Player player, Map<String, ActionPacket> packets, ItemActionEventFactory instance) {
        return new AbstractItemConsumeEvent(clickedItem,player,packets);
    }
}
