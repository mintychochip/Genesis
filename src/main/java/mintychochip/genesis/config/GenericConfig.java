package mintychochip.genesis.config;

import mintychochip.genesis.util.ConfigReader;
import mintychochip.genesis.util.EnumUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class GenericConfig {

    protected ConfigReader configReader;

    protected ConfigurationSection main;

    protected String path;

    public GenericConfig(String fileName, JavaPlugin plugin) {
        configReader = new ConfigReader(fileName,plugin);
    }

    public GenesisConfigurationSection getMain(String path) {
        return new GenesisConfigurationSection(configReader.getConfigurationSection(path));
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return configReader.getConfigurationSection(path);
    }

    public String getPath() {
        return path;
    }
}
