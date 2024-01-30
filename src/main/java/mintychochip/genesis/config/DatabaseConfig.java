package mintychochip.genesis.config;

import mintychochip.genesis.config.abstraction.GenericConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class DatabaseConfig extends GenericConfig {
    public DatabaseConfig(String path, JavaPlugin plugin) {
        super(path, plugin);
    }
}
