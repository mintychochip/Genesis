package mintychochip.genesis.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.Vector;
import org.yaml.snakeyaml.parser.ParserImpl;

public class ParticleShape { //allow polygons

    private final int vertices = 1;
    private Vector offset;
    private final double length;
    private final String z;
    private final String y;
    private final String x;
    private Location origin;
    private List<Vector> rotationAxes;
    private final Entity boundEntity;

    private double radius;
    private final List<Particle> particleList;
    private int points;

    private double degreesRotated;

    public ParticleShape(GenesisShape shape, List<Particle> particleList, Entity boundEntity) {
        this(boundEntity, particleList, shape.getX(), shape.getY(), shape.getZ(), shape.getLength(), shape.getRadius(), shape.getPoints());
    }

    public ParticleShape(Entity boundEntity, List<Particle> particleList, String x, String y, String z, double length, double radius, int points) {
        this.boundEntity = boundEntity;
        this.particleList = particleList;
        this.x = x;
        this.y = y;
        this.z = z;
        this.length = length;
        this.radius = radius;
        this.points = points;
        rotationAxes = null;
        particleList = null;
    }

    public double getPointDensity() {
        return length / points;
    }

    public int getVertices() {
        return vertices;
    }

    public Vector getOffset() {
        return offset;
    }

    public ParticleShape setOffset(Vector offset) {
        this.offset = offset;
        return this;
    }

    public double getLength() {
        return length;
    }

    public String getZ() {
        return z;
    }

    public String getY() {
        return y;
    }

    public String getX() {
        return x;
    }

    public Location getOrigin() {
        return origin;
    }

    public ParticleShape setOrigin(Location origin) {
        this.origin = origin;
        return this;
    }

    public List<Vector> getRotationAxes() {
        return rotationAxes;
    }

    public ParticleShape setRotationAxes(List<Vector> rotationAxes) {
        this.rotationAxes = rotationAxes;
        return this;
    }

    public Entity getBoundEntity() {
        return boundEntity;
    }

    public double getRadius() {
        return radius;
    }

    public ParticleShape setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public List<Particle> getParticleList() {
        return particleList;
    }

    public int getPoints() {
        return points;
    }

    public ParticleShape setPoints(int points) {
        this.points = points;
        return this;
    }

    public double getDegreesRotated() {
        return degreesRotated;
    }

    public ParticleShape setDegreesRotated(double degreesRotated) {
        this.degreesRotated = degreesRotated;
        return this;
    }

    public double rotate(double interval) {
        double original = degreesRotated;
        setDegreesRotated(degreesRotated += interval);
        return original;
    }

    public ParticleShape addRotationAxis(Vector vector) {
        if (rotationAxes == null) {
            rotationAxes = new ArrayList<>();
        }
        rotationAxes.add(vector);
        return this;
    }

    public ParticleShape setOffset(double x, double y, double z) {
        offset = new Vector(x, y, z);
        return this;
    }
}
