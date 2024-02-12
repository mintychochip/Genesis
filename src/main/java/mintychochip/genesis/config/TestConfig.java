package mintychochip.genesis.config;

import mintychochip.genesis.config.abstraction.GenericConfig;
import mintychochip.genesis.config.abstraction.GenesisConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class TestConfig extends GenericConfig {

    private GenesisConfigurationSection section = getMainConfigurationSection("field");
    public TestConfig(String path, JavaPlugin plugin) {
        super(path, plugin);
    }

    public GenesisConfigurationSection getSection() {
        return section;
    }
}
