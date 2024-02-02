package mintychochip.genesis;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import mintychochip.genesis.config.GenesisRegistry;
import mintychochip.genesis.listener.AbstractItemListener;
import mintychochip.genesis.listener.BindListener;
import mintychochip.genesis.listener.EntityDeathListener;
import mintychochip.genesis.listener.FlagListener;
import mintychochip.genesis.manager.GenesisConfigManager;
import mintychochip.genesis.manager.GenesisPlayerManager;
import mintychochip.genesis.manager.RecipeRegistry;
import mintychochip.genesis.particle.ParticleEngine;
import mintychochip.genesis.util.Keys;
import mintychochip.genesis.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Genesis extends JavaPlugin {

    private static Genesis instance;
    private static MathUtil mathUtil;
    private static Keys keys;

    private static DoubleEvaluator parser;

    private static GenesisPlayerManager genesisPlayerManager;

    private static ParticleEngine particleEngine;

    private static final int id = 0;

    private static final Map<Player, List<BukkitTask>> currentTasks = new HashMap<>();
    private static final RecipeRegistry itemManager = new RecipeRegistry();
    private static final List<CraftingRecipe> recipes = new ArrayList<>();

    public static RecipeRegistry getItemManager() {
        return itemManager;
    }

    public static GenesisPlayerManager getGenesisPlayerManager() {
        return genesisPlayerManager;
    }

    public static ParticleEngine getParticleManager() {
        return particleEngine;
    }

    public static Genesis getInstance() {
        return instance;
    }

    public static Keys getKeys() {
        return keys;
    }

    public static MathUtil getMath() {
        return mathUtil;
    }

    public static DoubleEvaluator getParser() {
        return parser;
    }

    public static Map<Player, List<BukkitTask>> getCurrentTasks() {
        return currentTasks;
    }

    public static NamespacedKey getKey(String key) {
        return Genesis.getKeys().getMap().get(key);
    }

    private static GenesisConfigManager genesisConfigManager;

    public static GenesisConfigManager getGenesisConfigManager() {
        return genesisConfigManager;
    }

    @Override
    public void onEnable() {
        mathUtil = new MathUtil();
        instance = this;
        keys = new Keys();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            GenesisRegistry.getLoadedPlugins().put(plugin.getName().toLowerCase(),plugin);
        }

        genesisPlayerManager = new GenesisPlayerManager();
        parser = new DoubleEvaluator();
        genesisConfigManager = new GenesisConfigManager(this);
        Bukkit.getPluginManager().registerEvents(new AbstractItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new FlagListener(), this);
        Bukkit.getPluginManager().registerEvents(new BindListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), this);
        keys.generateKeys(this, genesisConfigManager.getGenesisConfig());
        getCommand("mappings").setExecutor(new MappingCommand());
    }

    @Override
    public void onDisable() {
        Connection connection = genesisConfigManager.getDatabaseConfig().getDb().getConnection();
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
