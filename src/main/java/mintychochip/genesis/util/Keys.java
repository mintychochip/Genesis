package mintychochip.genesis.util;

import mintychochip.genesis.Genesis;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Keys {
    private Map<String, NamespacedKey> keys = new HashMap();
    public Keys() {
        NamespacedKey namespacedKey = new NamespacedKey(Genesis.getInstance(), "unstackable");
        keys.put("unstackable",namespacedKey);
    }
    public void generateKeys(JavaPlugin plugin, String[] keys) {
        for (String key : keys) {
            this.keys.put(key,new NamespacedKey(plugin,key));
        }
    }
    public void generateKeys(JavaPlugin plugin, List<String> keys) {
        for (String key : keys) {
            this.keys.put(key,new NamespacedKey(plugin,key));
        }

    }
    public NamespacedKey addKey(JavaPlugin plugin, String key) {
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
