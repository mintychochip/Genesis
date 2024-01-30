package mintychochip.genesis.config;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.config.abstraction.GenericConfig;
import mintychochip.genesis.config.abstraction.GenesisConfigurationSection;
import mintychochip.genesis.container.DropTableSettings;
import mintychochip.genesis.container.GenesisDropTableEntry;
import mintychochip.genesis.manager.GenesisConfigManager;
import mintychochip.genesis.util.EnumUtil;
import mintychochip.genesis.util.GenesisConfigMarker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Map;

public class DropTableConfig extends GenericConfig {

    private final GenesisConfigurationSection dropTableSection = getMainConfigurationSection("drop-table");
    private final GenesisConfigurationSection defaults = getMainConfigurationSection("default.settings");
    private final DropTableSettings defaultSettings = generateDropTableSettings(defaults);
    public DropTableConfig(String path, JavaPlugin plugin) {
        super(path, plugin);
        new BukkitRunnable() {
            public void run() {
                try {
                    loadDropTable(); //can do if here
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskLater(plugin, 10L);

    }

    public boolean loadDropTable() throws IOException {
        for (String key : dropTableSection.getKeys(false)) {
            if (EnumUtil.isInEnum(EntityType.class, key.toUpperCase())) {
                EntityType entityType = Enum.valueOf(EntityType.class, key.toUpperCase());
                GenesisConfigurationSection es = dropTableSection.getConfigurationSection(key);
                if (es == null) {
                    throw new IOException("Fields within configurationSection cannot be null!");
                }
                for (String dropTableKey : es.getKeys(false)) {
                    GenesisConfigurationSection itemSection = es.getConfigurationSection(dropTableKey);
                    String originator = itemSection.getString(GenesisConfigMarker.originator);
                    JavaPlugin javaPlugin = null;
                    if (originator != null) {
                        javaPlugin = (JavaPlugin) GenesisRegistry.getLoadedPlugins().get(originator.toLowerCase());
                    }
                    DropTableSettings tableSettings = new DropTableSettings().copy(defaultSettings);
                    ItemStack itemStackFromKey = getItemStackFromKey(dropTableKey, javaPlugin);
                    String inherits = itemSection.getString(GenesisConfigMarker.inherits);
                    if (inherits != null) {
                        DropTableSettings dropTableSettings = generateDropTableSettings(dropTableSection.getConfigurationSection(inherits));
                        tableSettings.copy(dropTableSettings);
                    }
                    GenesisConfigurationSection settings = itemSection.getConfigurationSection("settings");
                    if(settings != null) {
                        DropTableSettings dropTableSettings = generateDropTableSettings(settings);
                        tableSettings.copy(dropTableSettings);
                    }
                    GenesisRegistry.addDropTableEntry(entityType, new GenesisDropTableEntry(itemStackFromKey,tableSettings));
                }
            }
        }
        return true;
    }
    public DropTableSettings generateDropTableSettings(GenesisConfigurationSection settings) {
        return new DropTableSettings(settings.getDouble(GenesisConfigMarker.drop_rate),
                settings.getInt(GenesisConfigMarker.min_count),
                settings.getInt(GenesisConfigMarker.max_count),
                settings.getBoolean(GenesisConfigMarker.looting_applicable),
                settings.getBoolean(GenesisConfigMarker.player_kill));
    }

    public JavaPlugin getOriginator(String originator) {
        return (JavaPlugin) GenesisRegistry.getLoadedPlugins().get(originator);
    }

    public ItemStack getItemStackFromKey(String dropTableKey, JavaPlugin plugin) {
        if (plugin == null) {
            return new ItemStack(Material.valueOf(dropTableKey.toUpperCase()));
        }
        NamespacedKey namespacedKey = NamespacedKey.fromString(dropTableKey, plugin);
        if (Genesis.getItemManager().itemsContainsKey(namespacedKey)) {
            return Genesis.getItemManager().getRecipeItem(namespacedKey);
        }
        return new ItemStack(Material.valueOf(dropTableKey.toUpperCase()));
    }

    public boolean isMythic(String dropTableKey) {
        for (String s : dropTableKey.split(":")) {
            if (s.equalsIgnoreCase("mythic")) {
                return true;
            }
        }
        return false;
    }

    public GenesisConfigurationSection getDropTableSection() {
        return dropTableSection;
    }
}
