package mintychochip.genesis.manager;

import mintychochip.genesis.config.DatabaseConfig;
import mintychochip.genesis.config.DropTableConfig;
import mintychochip.genesis.config.GenesisConfig;
import mintychochip.genesis.config.RecipeConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class GenesisConfigManager extends GenericConfigManager {

    private final GenesisConfig genesisConfig;

    private final DatabaseConfig databaseConfig;

    private final DropTableConfig dropTableConfig;

    private final RecipeConfig recipeConfig;
    public GenesisConfigManager(JavaPlugin plugin) {
        super(plugin);
        dropTableConfig = new DropTableConfig("droptable.yml",plugin);
        genesisConfig = new GenesisConfig("genesis.yml",plugin);
        databaseConfig = new DatabaseConfig("database.yml",plugin);
        recipeConfig = new RecipeConfig("recipes.yml",plugin);
    }

    public GenesisConfig getGenesisConfig() {
        return genesisConfig;
    }

    public DropTableConfig getDropTableConfig() {
        return dropTableConfig;
    }

    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }
}
