package mintychochip.genesis.util;

import mintychochip.genesis.Genesis;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Keys {
    private Map<String, NamespacedKey> keys = new HashMap();
    public Keys() {
        generateKey(Genesis.getInstance(),"unstackable");
    }

    public NamespacedKey generateKey(JavaPlugin plugin, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        keys.put(key,namespacedKey);
        return namespacedKey;
    }

    public void putKey(NamespacedKey namespacedKey, String key) {
        keys.put(key,namespacedKey);
    }
    public Map<String, NamespacedKey> getMap() {
        return keys;
    }
}
