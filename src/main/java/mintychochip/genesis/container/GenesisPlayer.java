package mintychochip.genesis.container;

import org.bukkit.entity.Player;

public class GenesisPlayer {

    private Player player;

    private boolean particles;

    public GenesisPlayer setParticles(boolean particles) {
        this.particles = particles;
        return this;
    }

    public boolean isParticles() {
        return particles;
    }
}
