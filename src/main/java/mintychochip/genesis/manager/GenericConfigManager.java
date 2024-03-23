package mintychochip.genesis.manager;

import mintychochip.genesis.config.abstraction.GenesisConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class GenericConfigManager {

    private final JavaPlugin plugin;
    public GenericConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public GenesisConfigurationSection getConfig(String configName) {
        return null;
    }
}
