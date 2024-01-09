package mintychochip.genesis.config;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.color.GenesisTheme;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class GenesisConfig extends GenericConfig {

    private static int numberOfThemes = 5;
    private ConfigurationSection themes = configReader.getConfigurationSection("themes");
    public GenesisConfig(String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
        if(!loadThemes()) {
            //something
        }
    }

    private boolean loadThemes() {
        for (String key : themes.getKeys(false)) {
            String string = themes.getString(key);
            if(string == null) {
                Genesis.getInstance().getLogger().warning("Failed to load color themes");
                return false;
            }
            char[] charArray = string.toCharArray();
            if(charArray.length > numberOfThemes) {
                return false;
            }
            GenesisRegistry.getThemes().put(key.toUpperCase(),new GenesisTheme(charArray));
        }
        return true;
    }
}
