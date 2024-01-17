package mintychochip.genesis.config;

import mintychochip.genesis.util.ConfigReader;
import mintychochip.genesis.util.EnumUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Set;

public abstract class GenericConfig {

    protected final ConfigReader configReader;
    protected final String path;
    public GenericConfig(String path, JavaPlugin plugin) {
        configReader = new ConfigReader(path,plugin);
        this.path = path;
    }

    public GenesisConfigurationSection getMainConfigurationSection(String path) {
        return new GenesisConfigurationSection(configReader.getConfigurationSection(path),path);
    }

    public String getPath() {
        return path;
    }

    public ConfigReader getConfigReader() {
        return configReader;
    }
}
