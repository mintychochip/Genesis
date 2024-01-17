package mintychochip.genesis.container;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.util.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ItemData implements Serializable {

    protected String displayName;
    protected String key;

    public ItemData(String key) {
        this.key = key;
    }

    public ItemData setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }


}
