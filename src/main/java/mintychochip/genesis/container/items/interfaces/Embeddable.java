package mintychochip.genesis.container.items.interfaces;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public interface Embeddable {

    NamespacedKey key = null;

    NamespacedKey getKey();

    default String getType() {
        return this.getClass().getName();
    }
}
