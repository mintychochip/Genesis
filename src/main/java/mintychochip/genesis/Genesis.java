package mintychochip.genesis;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.google.gson.Gson;
import mintychochip.genesis.commands.abstraction.GenericMainCommandManager;
import mintychochip.genesis.commands.genesis.ExecuteCommandClickEvent;
import mintychochip.genesis.commands.genesis.GenerateFoodCommand;
import mintychochip.genesis.commands.genesis.ItemInfo;
import mintychochip.genesis.config.GenesisRegistry;
import mintychochip.genesis.events.ItemActionEventFactory;
import mintychochip.genesis.listener.*;
import mintychochip.genesis.manager.GenesisConfigManager;
import mintychochip.genesis.manager.RecipeRegistry;
import mintychochip.genesis.particle.ParticleEngine;
import mintychochip.genesis.util.Keys;
import mintychochip.genesis.util.MathUtil;
import mintychochip.genesis.util.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public final class Genesis extends JavaPlugin {

    private static Genesis instance;
    private static MathUtil mathUtil;
    private static Keys keys;
    private static DoubleEvaluator parser;
    private static ParticleEngine particleEngine;
    private static final Map<Player, List<BukkitTask>> currentTasks = new HashMap<>();

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    public static final Gson GSON = new Gson();

    public static Genesis getInstance() {
        return instance;
    }

    public static Keys getKeys() {
        return keys;
    }

    public static MathUtil getMath() {
        return mathUtil;
    }

    public static RecipeRegistry recipeRegistry;

    public static RecipeRegistry getItemManager() {
        return recipeRegistry;
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

    private static Serializer serializer;

    private final ItemActionEventFactory factory = new ItemActionEventFactory(this);

    private final Gson gson = new Gson();

    @Override
    public void onEnable() {
        parser = new DoubleEvaluator();
        mathUtil = new MathUtil(parser);
        instance = this;
        keys = new Keys();
        Genesis.getKeys().addKey(this, "items");
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            GenesisRegistry.getLoadedPlugins().put(plugin.getName().toLowerCase(), plugin);
        }
        GenericMainCommandManager main = new GenericMainCommandManager("genesis", "debug commands");
        main.addSubCommand(new ItemInfo("iinfo", "asdasdasdd"));
        main.addSubCommand(new GenerateFoodCommand("test","test"));
        recipeRegistry = new RecipeRegistry();
        genesisConfigManager = new GenesisConfigManager(this, recipeRegistry);
        registerListeners();
        keys.generateKeys(this, genesisConfigManager.getGenesisConfig());
        getCommand("mappings").setExecutor(new MappingCommand(recipeRegistry));
        getCommand("genesis").setExecutor(main);
        main.addSubCommand(new ExecuteCommandClickEvent("click", "aasdsd", gson));
        serializer = new Serializer(gson);
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new AbstractItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new FlagListener(), this);
        Bukkit.getPluginManager().registerEvents(new BindListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemExecutionListener(factory), this);
        Bukkit.getPluginManager().registerEvents(new CancellationListener(), this);
    }

    public static Serializer getSerializer() {
        return serializer;
    }

    @Override
    public void onDisable() {
    }
}
