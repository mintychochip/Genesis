package mintychochip.genesis.config;

import mintychochip.genesis.config.abstraction.GenericConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftingRecipeConfig extends GenericConfig {
    public CraftingRecipeConfig(String path, JavaPlugin plugin) {
        super(path, plugin);
    }
}
