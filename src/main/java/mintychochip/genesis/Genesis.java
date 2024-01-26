package mintychochip.genesis;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import mintychochip.genesis.config.GenesisConfig;
import mintychochip.genesis.listener.AbstractItemListener;
import mintychochip.genesis.listener.BindListener;
import mintychochip.genesis.listener.FlagListener;
import mintychochip.genesis.manager.GenesisPlayerManager;
import mintychochip.genesis.manager.RecipeRegistry;
import mintychochip.genesis.particle.ParticleEngine;
import mintychochip.genesis.util.ConfigMarker;
import mintychochip.genesis.util.Keys;
import mintychochip.genesis.util.MathUtil;
import mintychochip.genesis.util.StripLog;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

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
    private static GenesisConfig genesisConfig;
    private static final Map<Player, List<BukkitTask>> currentTasks = new HashMap<>();
    private static final RecipeRegistry itemManager = new RecipeRegistry();
    private static final List<CraftingRecipe> recipes = new ArrayList<>();
    public static GenesisConfig getGenesisConfig() {
        return genesisConfig;
    }

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

    @Override
    public void onEnable() {
        mathUtil = new MathUtil();
        instance = this;
        keys = new Keys();
        genesisPlayerManager = new GenesisPlayerManager();
        genesisConfig = new GenesisConfig("genesis.yml", this);
        parser = new DoubleEvaluator();
        Bukkit.getPluginManager().registerEvents(new AbstractItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new FlagListener(), this);
        Bukkit.getPluginManager().registerEvents(new BindListener(), this);
        keys.generateKeys(this, genesisConfig);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
