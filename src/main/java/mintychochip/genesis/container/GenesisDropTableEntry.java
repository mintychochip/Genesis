package mintychochip.genesis.container;

import org.bukkit.inventory.ItemStack;

public class GenesisDropTableEntry {

    private ItemStack itemStack;
    private DropTableSettings dropTableSettings;
    public GenesisDropTableEntry(ItemStack itemStack, DropTableSettings dropTableSettings) {
        this.itemStack = itemStack;
        this.dropTableSettings = dropTableSettings;
    }

    public DropTableSettings getDropTableSettings() {
        return dropTableSettings;
    }

    public void copyDropTableSettings(DropTableSettings dropTableSettings) {
        this.dropTableSettings.copy(dropTableSettings);
    }

    public void setDropTableSettings(DropTableSettings dropTableSettings) {
        this.dropTableSettings = dropTableSettings;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
