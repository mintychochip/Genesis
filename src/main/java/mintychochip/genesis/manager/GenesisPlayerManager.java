package mintychochip.genesis.manager;

import mintychochip.genesis.container.GenesisPlayer;

import java.util.ArrayList;
import java.util.List;

public class GenesisPlayerManager {

    private final List<GenesisPlayer> genesisPlayerList = new ArrayList<>();

    public List<GenesisPlayer> getGenesisPlayerList() {
        return genesisPlayerList;
    }
}
