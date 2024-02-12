package mintychochip.genesis.container;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface Embeddable {
    String key = null;

    String getKey();

    void serialize(ItemStack itemStack);

    void serialize(ItemMeta itemMeta);
}
