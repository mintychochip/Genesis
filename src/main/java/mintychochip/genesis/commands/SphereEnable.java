package mintychochip.genesis.commands;

import mintychochip.genesis.commands.abstraction.GenericCommandObject;
import mintychochip.genesis.commands.abstraction.SubCommand;
import mintychochip.genesis.particle.*;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class SphereEnable extends GenericCommandObject implements SubCommand {

    private final ParticleEngine particleEngine;
    public SphereEnable(String executor, String description, ParticleEngine particleEngine) {
        super(executor, description);
        this.particleEngine = particleEngine;
    }

    @Override
    public boolean execute(String[] strings, Player sender) {
        if(strings.length < depth) {
            return false;
        }
        List<Particle> particleList = new ArrayList<>();
        particleList.add(Particle.REDSTONE);
        ParticleShape particleShape1 = new ParticleShape(GenesisShape.SPHERE, 2, 2, new Vector(0, 1, 0), particleList, sender);
        ShapeMeta shapeMeta1 = particleShape1.getShapeMeta().addRotationAxis(1, 0, 0).addRotationAxis(0, 1, 0);
        particleShape1.setShapeMeta(shapeMeta1);
        ParticlePackage particlePackage = new ParticlePackage(particleShape1, 3L, 1L);
        BukkitTask render = particleEngine.render(particleShape1, 3L, 1L, sender, Color.AQUA);
        return true;
    }
}
