package mintychochip.genesis.manager;

import mintychochip.genesis.config.*;
import org.bukkit.plugin.java.JavaPlugin;

public class GenesisConfigManager extends GenericConfigManager {

    private final GenesisConfig genesisConfig;
    private final RecipeConfig recipeConfig;

    private final DamageTypeConfig damageTypeConfig;

    private final DropTableConfig dropTableConfig;
    private final TestConfig testConfig;
    public GenesisConfigManager(JavaPlugin plugin, RecipeRegistry recipeRegistry) {
        super(plugin);
        dropTableConfig = new DropTableConfig("droptable.yml",plugin, recipeRegistry);
        testConfig = new TestConfig("test.yml",plugin);
        genesisConfig = new GenesisConfig("genesis.yml",plugin, recipeRegistry);
        recipeConfig = new RecipeConfig("recipes.yml",plugin);
        damageTypeConfig = new DamageTypeConfig("damagetypes.yml",plugin);
    }

    public GenesisConfig getGenesisConfig() {
        return genesisConfig;
    }

    public TestConfig getTestConfig() {
        return testConfig;
    }
}
