package mintychochip.genesis.config;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.color.GenesisTheme;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class GenesisConfig extends GenericConfig {

    private ConfigurationSection themes = configReader.getConfigurationSection("themes");
    public GenesisConfig(String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
        loadThemes();
    }

    private void loadThemes() {
        for (String key : themes.getKeys(false)) {
            String string = themes.getString(key);
            if(string == null) {
                Genesis.getInstance().getLogger().warning("Failed to load color themes");
                return;
            }
            char[] charArray = string.toCharArray();
            if(charArray.length < 5) {
                char[] newArr = {'7','7','7','7','7'};
                for(int i = 0; i < charArray.length; i++) {
                    newArr[i] = charArray[i];
                }
                charArray = newArr;
            }
            GenesisTheme genesisTheme = new GenesisTheme();
            for (int i = 0; i < charArray.length; i++) {
                switch (i) {
                    case 0: genesisTheme.setHeaderColor(charArray[i]);
                    case 1: genesisTheme.setSecondaryHeaderColor(charArray[i]);
                    case 2: genesisTheme.setBodyColor(charArray[i]);
                    case 3: genesisTheme.setAccents(charArray[i]);
                    case 4: genesisTheme.setBullets(charArray[i]);
                }
            }
            GenesisRegistry.getThemes().put(key.toUpperCase(),genesisTheme);
        }
    }
}
