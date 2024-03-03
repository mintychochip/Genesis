package mintychochip.genesis.container;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.container.items.interfaces.Embeddable;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class ItemData implements Embeddable {

    private static final NamespacedKey key = Genesis.getKey("items");

    private String type = this.getClass().getName();
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public String getType() {
        return null;
    }
}
