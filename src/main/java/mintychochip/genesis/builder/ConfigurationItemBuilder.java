package mintychochip.genesis.builder;

import mintychochip.genesis.config.abstraction.GenesisConfigurationSection;
import mintychochip.genesis.container.AbstractItem;
import mintychochip.genesis.util.ConfigMarker;
import mintychochip.genesis.util.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ConfigurationItemBuilder extends ItemBuilder {
    protected final GenesisConfigurationSection main;
    protected final Material defaultMaterial;
    public ConfigurationItemBuilder(AbstractItem abstractItem, String genesisTheme, GenesisConfigurationSection main) {
        super(abstractItem, genesisTheme);
        this.main = main;
        this.defaultMaterial = abstractItem.getItemStack().getType();
    }
    public ConfigurationItemBuilder(JavaPlugin instance, String genesisTheme, GenesisConfigurationSection main) {
        this(new AbstractItem(instance,main.enumFromSection(Material.class,ConfigMarker.material)),genesisTheme,main);
    }
    public ConfigurationItemBuilder setDisplayName(String displayName, char colorCode) {
        return (ConfigurationItemBuilder) super.setDisplayName(displayName,colorCode);
    }
    public ConfigurationItemBuilder setCustomModelData(int model) {
        return (ConfigurationItemBuilder) super.setCustomModelData(model);
    }
    public ConfigurationItemBuilder setUnbreakable(boolean unbreakable) {
        return (ConfigurationItemBuilder) super.setUnbreakable(unbreakable);
    }
    public ConfigurationItemBuilder addLore(List<String> text) {
        return (ConfigurationItemBuilder) super.addLore(text);
    }
    public ConfigurationItemBuilder addLore(String text) {
        return (ConfigurationItemBuilder) super.addLore(text);
    }
    public ConfigurationItemBuilder defaultBuilder() {
        return this.setDisplayName(main.getString(ConfigMarker.display_name),main.enumFromSection(Rarity.class, ConfigMarker.rarity).getColorCode())
                .setCustomModelData(main.getInt(ConfigMarker.custom_model))
                .setUnbreakable(main.getBoolean(ConfigMarker.unbreakable))
                .addLore(main.getStringList(ConfigMarker.lore));
    }
    public ItemStack defaultBuild() {
        return defaultBuilder().build();
    }
}
