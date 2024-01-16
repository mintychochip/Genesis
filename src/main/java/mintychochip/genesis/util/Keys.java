package mintychochip.genesis.util;

import mintychochip.genesis.Genesis;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Keys {
    private Map<String, NamespacedKey> keys = new HashMap();

    public Keys() {
        generateKey(Genesis.getInstance(), "unstackable");
    }

    public NamespacedKey generateKey(JavaPlugin plugin, String key) {
        return new NamespacedKey(plugin, key);
    }

    public void putKey(NamespacedKey namespacedKey, String key) {
        keys.put(key, namespacedKey);
    }

    public Map<String, NamespacedKey> getMap() {
        return keys;
    }

    public boolean assignStringToItemStack(String genesisKey, String value, ItemStack itemStack) {
        return assignGenericToItemStack(genesisKey,PersistentDataType.STRING,value,itemStack);
    }
    public boolean assignIntegerToItemStack(String genesisKey, Integer value, ItemStack itemStack) {
        return assignGenericToItemStack(genesisKey,PersistentDataType.INTEGER,value,itemStack);
    }
    public boolean assignByteArrayToItemStack(String genesisKey, byte[] value, ItemStack itemStack) {
        return assignGenericToItemStack(genesisKey,PersistentDataType.BYTE_ARRAY,value,itemStack);
    }
    public boolean assignBooleanToItemStack(String genesisKey, boolean value, ItemStack itemStack) {
        return assignGenericToItemStack(genesisKey,PersistentDataType.BOOLEAN,value,itemStack);
    }
    public <T> boolean assignGenericToItemStack(String genesisKey, PersistentDataType persistentDataType, T value, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return false;
        }
        if(keys.get(genesisKey) == null) {
            return false;
            //can do error message here
        }
        itemMeta.getPersistentDataContainer().set(keys.get(genesisKey),persistentDataType,value);
        itemStack.setItemMeta(itemMeta);
        return true;
    }
}
