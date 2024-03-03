package mintychochip.genesis.listener;

import mintychochip.genesis.events.AbstractItemClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class CancellationListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    private void onAbstractItemOccur(final AbstractItemClickEvent event) {

    }
}
