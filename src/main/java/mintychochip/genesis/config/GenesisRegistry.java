package mintychochip.genesis.config;

import mintychochip.genesis.color.GenesisTheme;
import mintychochip.genesis.container.GenesisDropTableEntry;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenesisRegistry {

    private static Map<String, Plugin> loadedPlugins = new HashMap<>();
    private static final Map<String, GenesisTheme> themes = new HashMap<>();

    private static final Map<EntityType, List<GenesisDropTableEntry>> dropTableEntries = new HashMap<>();
    public static Map<String, GenesisTheme> getThemes() {
        return themes;
    }
    public static void addDropTableEntry(EntityType type, GenesisDropTableEntry entry) {
        List<GenesisDropTableEntry> genesisDropTableEntries = dropTableEntries.get(type);
        if(genesisDropTableEntries == null) {
            genesisDropTableEntries = new ArrayList<>();
        }
        genesisDropTableEntries.add(entry);
        dropTableEntries.put(type,genesisDropTableEntries);
    }

    public static Map<String, Plugin> getLoadedPlugins() {
        return loadedPlugins;
    }

    public static Map<EntityType, List<GenesisDropTableEntry>> getDropTableEntries() {
        return dropTableEntries;
    }
}
