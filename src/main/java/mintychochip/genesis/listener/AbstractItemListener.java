package mintychochip.genesis.listener;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.particle.*;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class AbstractItemListener implements Listener {

    //EventHandler
    public void playerInteractEvent(final PlayerInteractEvent event) {
        List<Particle> particleList = new ArrayList<>();
        particleList.add(Particle.REDSTONE);
        ParticleShape particleShape1 = new ParticleShape(GenesisShape.SPHERE, 2, 2, particleList, event.getPlayer());
        ShapeMeta shapeMeta1 = particleShape1.getShapeMeta().addRotationAxis(1,0,0).addRotationAxis(0,1,0);
        particleShape1.setShapeMeta(shapeMeta1);
        ParticlePackage particlePackage = new ParticlePackage(particleShape1, 3L, 1L);
        ParticleEngine.render(particleShape1,3L,1L,event.getPlayer());
    }
    @EventHandler
    public void onPlayerLeave(final PlayerQuitEvent event) {
    }

    @EventHandler
    public void onGenesisPlayerJoin(final PlayerJoinEvent event) {
        event.getPlayer();
    }
}
