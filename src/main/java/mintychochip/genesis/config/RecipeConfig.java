package mintychochip.genesis.config;

import mintychochip.genesis.config.abstraction.GenericConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class RecipeConfig extends GenericConfig {
    public RecipeConfig(String path, JavaPlugin plugin) {
        super(path, plugin);
    }
}
