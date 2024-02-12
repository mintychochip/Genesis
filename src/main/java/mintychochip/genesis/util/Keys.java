package mintychochip.genesis.util;

import mintychochip.genesis.config.abstraction.GenericConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Keys {
    private final Map<String, NamespacedKey> keys = new HashMap();

    @Deprecated
    public void generateKeys(JavaPlugin plugin, String[] keys) {
        for (String key : keys) {
            this.keys.put(key, new NamespacedKey(plugin, key));
        }
    }

    public void generateKeys(JavaPlugin plugin, GenericConfig config) {
        generateKeys(plugin, config.getMainConfigurationSection(ConfigMarker.global_settings).getStringList(ConfigMarker.keys));
    }

    public void generateKeys(JavaPlugin plugin, List<String> keys) {
        for (String key : keys) {
            this.keys.put(key, new NamespacedKey(plugin, key));
        }
    }

    @Deprecated
    public NamespacedKey addKey(JavaPlugin plugin, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        keys.put(key, namespacedKey);
        return namespacedKey;
    }
    public void putKey(NamespacedKey namespacedKey, String key) {
        keys.put(key, namespacedKey);
    }

    public Map<String, NamespacedKey> getMap() {
        return keys;
    }

    public <T> boolean assignGenericToItemStack(String key, PersistentDataType dataType, T value, ItemStack assign) {
        if (assign == null) {
            return false;
        }
        if (key == null) {
            return false;
        }
        if (!keys.containsKey(key)) {
            return false;
        }
        ItemMeta itemMeta = assign.getItemMeta();
        if (itemMeta == null) {
            return false;
        }
        itemMeta.getPersistentDataContainer().set(keys.get(key), dataType, value);
        return assign.setItemMeta(itemMeta);
    }
    public <T> boolean assignGenericToItemMeta(String key, PersistentDataType dataType, T value, ItemMeta itemMeta) {
        if(itemMeta == null) {
            return false;
        }
        if (key == null) {
            return false;
        }
        if (!keys.containsKey(key)) {
            return false;
        }
        itemMeta.getPersistentDataContainer().set(keys.get(key), dataType, value);
        return true;
    }

    public boolean assignStringToItemStack(String key, String value, ItemStack assign) {
        return assignGenericToItemStack(key, PersistentDataType.STRING, value, assign);
    }

    public boolean assignByteArrayToItemStack(String key, byte[] value, ItemStack assign) {
        return assignGenericToItemStack(key, PersistentDataType.BYTE_ARRAY, value, assign);
    }
    public boolean assignByteArrayToItemMeta(String key, byte[] value, ItemMeta assign) {
        return assignGenericToItemMeta(key,PersistentDataType.BYTE_ARRAY,value,assign);
    }

    public boolean assignIntegerToItemStack(String key, int value, ItemStack assign) {
        return assignGenericToItemStack(key, PersistentDataType.INTEGER, value, assign);
    }

    public boolean assignBooleanToItemStack(String key, boolean value, ItemStack assign) {
        return assignGenericToItemStack(key, PersistentDataType.BOOLEAN, value, assign);
    }
}
