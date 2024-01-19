package mintychochip.genesis.container;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class AbstractItem {
    protected final JavaPlugin instance;
    private final ItemStack itemStack;
    private final boolean boundOnCraft;
    protected ItemMeta itemMeta;

    public AbstractItem(JavaPlugin instance, Material material) {
        this(instance, material, true);
    }

    public AbstractItem(JavaPlugin instance, Material material, boolean boundOnCraft) {
        this.instance = instance;
        itemStack = new ItemStack(material);
        this.boundOnCraft = boundOnCraft;
        itemMeta = itemStack.getItemMeta();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public AbstractItem setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        return this;
    }

    public JavaPlugin getInstance() {
        return instance;
    }

    public boolean isBoundOnCraft() {
        return boundOnCraft;
    }
}
