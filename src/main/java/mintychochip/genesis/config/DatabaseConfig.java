package mintychochip.genesis.config;

import mintychochip.genesis.config.abstraction.GenericConfig;
import mintychochip.genesis.config.abstraction.GenesisConfigurationSection;
import mintychochip.genesis.container.database.GenericDatabase;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class DatabaseConfig extends GenericConfig {

    private String username;
    private String password;
    @NotNull
    private String url;

    private GenericDatabase db;
    public DatabaseConfig(String path, JavaPlugin plugin) {
        super(path, plugin);
        GenesisConfigurationSection database = getMainConfigurationSection("database");
        db = new GenericDatabase(this);
    }

    public GenericDatabase getDb() {
        return db;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

}
