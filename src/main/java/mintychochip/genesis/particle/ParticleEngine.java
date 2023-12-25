package mintychochip.genesis.particle;

import com.fathzer.soft.javaluator.StaticVariableSet;
import mintychochip.genesis.Genesis;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class ParticleEngine {


    public static BukkitTask render(ParticleShape shape, long delay, long interval) {
        return new BukkitRunnable() {
            public void run() {
                Location location = shape.getBoundEntity().getLocation();
                Vector offset = shape.getOffset();
                if(offset != null) {
                    location.add(offset);
                }
                for (double i = 0; i <= shape.getLength(); i += shape.getPointDensity()) {
                    Vector vector = calculateOffsetVector(shape, i);
                    location.getWorld().spawnParticle(shape.getParticleList().get(0), location.add(vector), 1, 0, 0, 0, 0, new Particle.DustOptions(Color.MAROON, 0.5F), true);
                    location.subtract(vector);
                }
                shape.rotate(0.05f);
            }
        }.runTaskTimer(Genesis.getInstance(), delay, interval);
    }

    public static double circleExpressionEvaluation(double radius, double time, String function) {
        StaticVariableSet<Double> variables = new StaticVariableSet<>();
        variables.set("radius", radius);
        variables.set("i", time);
        return Genesis.getParser().evaluate(function, variables);
    }

    public static Vector calculateOffsetVector(ParticleShape shape, double time) {
        double radius = shape.getRadius();
        double x = circleExpressionEvaluation(radius, time, shape.getX());
        double y = circleExpressionEvaluation(radius, time, shape.getY());
        double z = circleExpressionEvaluation(radius, time, shape.getZ());
        Vector vector = new Vector(x, y, z);
        if(shape.getRotationAxes() != null) {
            for (Vector axis : shape.getRotationAxes()) {
                vector.rotateAroundNonUnitAxis(axis,shape.getDegreesRotated());
            }
        }
        return vector;
    }
}
