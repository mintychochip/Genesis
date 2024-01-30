package mintychochip.genesis.manager;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class GenericConfigManager {

    private final JavaPlugin plugin;
    public GenericConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
