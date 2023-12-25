package mintychochip.genesis.listener;

import mintychochip.genesis.particle.GenesisShape;
import mintychochip.genesis.particle.ParticleEngine;
import mintychochip.genesis.particle.ParticleShape;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class AbstractItemListener implements Listener {

    @EventHandler
    public void playerInteractEvent(final PlayerInteractEvent event) {
        List<Particle> list = new ArrayList<>();
        list.add(Particle.REDSTONE);
        ParticleShape particleShape = new ParticleShape(GenesisShape.SPHERE, list, event.getPlayer())
                .setPoints(200)
                .setOffset(0,1.25,0)
                .addRotationAxis(new Vector(0,1,0))
                .addRotationAxis(new Vector(1,0,0));
        BukkitTask render = ParticleEngine.render(particleShape, 3L, 1L);
    }
}
