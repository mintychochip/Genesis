package mintychochip.genesis.util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigReader {

    private final String fileName;
    private final JavaPlugin plugin;
    private String currentPath;
    private File file;
    private YamlConfiguration config;

    public ConfigReader(String fileName, JavaPlugin plugin) {
        this.fileName = fileName;
        this.plugin = plugin;
        load();
    }

    public void load() {
        file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);
        reload();
    }

    public void reload() { //make this less general
        try {
            config.load(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set(String path, Object val) {
        config.set(path, val);
        save();
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public ConfigurationSection getConfigurationSection(String header) {
        return config.getConfigurationSection(header);
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }
}
