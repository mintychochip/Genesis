package mintychochip.genesis.container;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.color.GenesisTheme;
import mintychochip.genesis.config.GenesisRegistry;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class AbstractItem {
    protected final JavaPlugin instance;
    protected ItemMeta itemMeta;

    private final ItemStack itemStack;

    private final boolean boundOnCraft;

    public AbstractItem(JavaPlugin instance, Material material, boolean boundOnCraft) {
        this.instance = instance;
        itemStack = new ItemStack(material);
        this.boundOnCraft = boundOnCraft;
        itemMeta = itemStack.getItemMeta();
    }

    public AbstractItem setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public JavaPlugin getInstance() {
        return instance;
    }

    public boolean isBoundOnCraft() {
        return boundOnCraft;
    }
}
