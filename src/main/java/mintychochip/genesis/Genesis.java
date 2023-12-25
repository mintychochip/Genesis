package mintychochip.genesis;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import mintychochip.genesis.config.GenesisConfig;
import mintychochip.genesis.listener.AbstractItemListener;
import mintychochip.genesis.particle.ParticleEngine;
import mintychochip.genesis.util.Keys;
import mintychochip.genesis.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
public final class Genesis extends JavaPlugin {

    private static Genesis instance;
    private static MathUtil mathUtil;
    private static Keys keys;

    private static DoubleEvaluator parser;

    private static ParticleEngine particleEngine;
    private static GenesisConfig genesisConfig;
    public static GenesisConfig getGenesisConfig() {
        return genesisConfig;
    }

    @Override
    public void onEnable() {
        mathUtil = new MathUtil();
        instance = this;
        keys = new Keys();
        //asdasdas
        //test edit
        keys.generateKey(this,"abstractItem");
        genesisConfig = new GenesisConfig("genesis.yml",this);
        parser = new DoubleEvaluator();
        Bukkit.getPluginManager().registerEvents(new AbstractItemListener(),this);
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

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
