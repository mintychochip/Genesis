package mintychochip.genesis;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import mintychochip.genesis.config.GenesisConfig;
import mintychochip.genesis.listener.AbstractItemListener;
import mintychochip.genesis.manager.GenesisPlayerManager;
import mintychochip.genesis.particle.ParticleEngine;
import mintychochip.genesis.util.Keys;
import mintychochip.genesis.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
    private static GenesisConfig genesisConfig;
    public static GenesisConfig getGenesisConfig() {
        return genesisConfig;
    }
    private static Map<Player,List<BukkitTask>> currentTasks = new HashMap<>();
    @Override
    public void onEnable() {
        mathUtil = new MathUtil();
        instance = this;
        keys = new Keys();
        //asdasdas
        //test edit
        genesisPlayerManager = new GenesisPlayerManager();
        keys.generateKey(this,"abstractItem");
        genesisConfig = new GenesisConfig("genesis.yml",this);
        parser = new DoubleEvaluator();
        Bukkit.getPluginManager().registerEvents(new AbstractItemListener(),this);
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

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
