package mintychochip.genesis.events;

import mintychochip.genesis.container.items.AbstractItem;
import mintychochip.genesis.container.items.actions.ActionPacket;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import java.util.Map;

public class ItemActionEventFactory { //prevents rogue itemaction injection


    public ItemActionEventFactory(JavaPlugin plugin) {
        //check if plugin is genesis
    }
    public AbstractItemActivationEvent createInstance(AbstractItem item, Player player, Map<String, ActionPacket> packets, ActionEventType type) {
       switch (type) {
            case CLICK -> {
                return AbstractItemClickEvent.createInstance(item, player, packets, this);
            }
            case CONSUME -> {
                return AbstractItemConsumeEvent.createInstance(item, player, packets, this);
            }
        }
        return null;
    }
}
