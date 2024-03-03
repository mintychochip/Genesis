package mintychochip.genesis.util;

import com.google.gson.Gson;
import mintychochip.genesis.Genesis;
import mintychochip.genesis.container.items.interfaces.Embeddable;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;

public class Serializer {

    private final Gson gson;

    public Serializer(Gson gson) {
        this.gson = gson;
    }

    public <T extends Embeddable> T deserialize(String key, ItemStack itemStack, Class<T> aClass) {
        if(itemStack == null) {
            return null;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta == null) {
            return null;
        }
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        if(persistentDataContainer.has(Genesis.getKey(key),PersistentDataType.STRING)) {
            return gson.fromJson(persistentDataContainer.get(Genesis.getKey(key), PersistentDataType.STRING),aClass);
        }
        return null;
    }
    public <T extends Embeddable> boolean serializeToItem(T data, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return false;
        }
        if(!serializeToMeta(data, itemMeta)) {
            return false;
        }
        return itemStack.setItemMeta(itemMeta);
    }

    public <T extends Embeddable> boolean serializeToMeta(T data, ItemMeta itemMeta) {
        if (itemMeta == null) {
            return false;
        }
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        persistentDataContainer.set(data.getKey(), PersistentDataType.STRING, gson.toJson(data));
        return true;
    }
}
