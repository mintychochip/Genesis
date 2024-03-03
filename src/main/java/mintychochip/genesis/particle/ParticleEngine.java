package mintychochip.genesis.particle;

import com.fathzer.soft.javaluator.StaticVariableSet;
import mintychochip.genesis.Genesis;
import mintychochip.genesis.util.MathUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ParticleEngine {

    private final MathUtil mathUtil;
    public ParticleEngine(MathUtil mathUtil) {
        this.mathUtil = mathUtil;
    }
    public List<BukkitTask> renderAll(List<ParticlePackage> packages, Player player, Color color) {
        List<BukkitTask> taskList = new ArrayList<>();
        for (ParticlePackage particlePackage : packages) {
            taskList.add(render(particlePackage.getParticleShape(), particlePackage.getDelay(), particlePackage.getInterval(), player, color));
        }
        return taskList;
    }

    public BukkitTask render(ParticleShape shape, long delay, long interval, Player player, Color color) {
        return new BukkitRunnable() {
            final Location dir = player.getEyeLocation();
            Location location = shape.getBoundLocation();

            public void run() {
                ShapeMeta shapeMeta = shape.getShapeMeta();
                if (shape.getBoundLocation() == null) {
                    location = shape.getBoundEntity().getLocation();
                }
                Vector offset = shape.getOffset();
                if (offset != null) {
                    location.add(offset);
                }
                for (double i = 0; i <= shapeMeta.getLength(); i += shapeMeta.getLength() / shapeMeta.getPoints()) {
                    Vector vector = calculateOffsetVector(shape, i);
                    vector = MathUtil.rotateFunction(vector, dir);
                    Location add = location.add(vector);
                    location.getWorld().spawnParticle(shape.getParticleList().get(0), add, 1, 0, 0, 0, 0, new Particle.DustOptions(color, 0.5F), true);

                    location.subtract(vector);
                }
                shapeMeta.rotate(360 / shapeMeta.getRotationSpeed());
                if (shapeMeta.getRadiusIncrement() != 0) {
                    shape.setShapeMeta(shapeMeta.incrementRadius());
                }
            }
        }.runTaskTimer(Genesis.getInstance(), delay, interval);
    }

    public static void drawVertex(Location start, Location end) {
        new BukkitRunnable() {
            final Vector dir = end.subtract(start).toVector().normalize();

            public void run() {
                for (double i = 0.5; i <= 50; i += 0.5) {
                    dir.multiply(i);
                    start.getWorld().spawnParticle(Particle.FLAME, start.add(dir), 1, 0, 0, 0, 0, null, true);
                    start.subtract(dir);
                    dir.normalize();
                }
            }
        }.runTaskTimer(Genesis.getInstance(), 3L, 1L);
    }

    public static void drawVertex(World world, double x1, double x2, double y1, double y2, double z1, double z2) {
        Location location = new Location(world, x1, y1, z1);
        Vector dir = new Location(world, x2, y2, z2).subtract(location).toVector();
        for (double i = 0; i < 10; i += 0.5) {
            dir.multiply(i);
            location.add(dir);
            location.getWorld().spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0, null, true);
            location.subtract(dir);
            dir.normalize();
        }

    }

    private static boolean check(ParticleShape shape) {
        ShapeMeta shapeMeta = shape.getShapeMeta();
        Bukkit.broadcastMessage(shapeMeta.getRadius() + " " + shape.getMaxRadius());
        return shapeMeta.getRadius() >= shape.getMaxRadius();

    }

    public double circleExpressionEvaluation(double radius, double time, String function) {
        StaticVariableSet<Double> variables = new StaticVariableSet<>();
        variables.set("i", time);
        return radius * mathUtil.evaluateFunction(function, variables);
    }

    public Vector calculateOffsetVector(ParticleShape shape, double time) {
        ShapeMeta shapeMeta = shape.getShapeMeta();
        double radius = shapeMeta.getRadius();
        double x = circleExpressionEvaluation(radius, time, shape.getX());
        double y = circleExpressionEvaluation(radius, time, shape.getY());
        double z = circleExpressionEvaluation(radius, time, shape.getZ());
        Vector vector = new Vector(x, y, z);
        if (shapeMeta.getRotationAxes() != null) {
            for (Vector axis : shapeMeta.getRotationAxes()) {
                vector.rotateAroundNonUnitAxis(axis, shapeMeta.getDegreesRotated());
            }
        }
        return vector;
    }
}
