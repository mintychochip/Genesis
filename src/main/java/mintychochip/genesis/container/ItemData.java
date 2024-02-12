package mintychochip.genesis.container;

import mintychochip.genesis.util.Rarity;
import mintychochip.genesis.util.Serializer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.Serial;
import java.io.Serializable;

public class ItemData implements Serializable, Embeddable {
    @Serial
    private static final long serialVersionUID = 123123;
    private static final String key = "items";

    public String toString() {
        return key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void serialize(ItemStack itemStack) {
        Serializer.serializeToItem(this, itemStack);
    }

    @Override
    public void serialize(ItemMeta itemMeta) {
        Serializer.serializeToMeta(this, itemMeta);
    }
}
