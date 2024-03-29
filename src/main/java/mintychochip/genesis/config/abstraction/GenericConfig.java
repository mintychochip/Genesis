package mintychochip.genesis.config.abstraction;

import mintychochip.genesis.util.ConfigReader;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class GenericConfig {
    protected final ConfigReader configReader;
    protected final String path;
    public GenericConfig(String path, JavaPlugin plugin) {
        configReader = new ConfigReader(path, plugin);
        this.path = path;
    }

    public GenesisConfigurationSection getMainConfigurationSection(String path) {
        return new GenesisConfigurationSection(configReader.getConfigurationSection(path), path);
    }

    public String getPath() {
        return path;
    }

    public ConfigReader getConfigReader() {
        return configReader;
    }
}
