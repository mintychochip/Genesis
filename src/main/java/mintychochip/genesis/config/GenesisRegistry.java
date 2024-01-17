package mintychochip.genesis.config;

import mintychochip.genesis.color.GenesisTheme;
import mintychochip.genesis.container.GenesisPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenesisRegistry {

    private static final Map<String, GenesisTheme> themes = new HashMap<>();
    public static Map<String, GenesisTheme> getThemes() {
        return themes;
    }
}
