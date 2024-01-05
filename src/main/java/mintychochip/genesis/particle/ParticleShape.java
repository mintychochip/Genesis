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

    private final double maxRadius;
    private final double maxDistance;
    private final Vector offset;
    private final String z;
    private final String y;
    private final String x;
    private final List<Particle> particleList;
    private Entity boundEntity = null;
    private Location boundLocation = null;
    private ShapeMeta shapeMeta;

    public ParticleShape(GenesisShape shape, double maxRadius, double maxDistance, List<Particle> particleList, Entity boundEntity) {
        this(shape,maxDistance,maxRadius,null,particleList, boundEntity);
    }
    public ParticleShape(GenesisShape shape, double maxRadius, double maxDistance, Vector offset, List<Particle> particleList, Entity boundEntity) {
        this.x = shape.getX();
        this.y = shape.getY();
        this.z = shape.getZ();
        this.maxDistance = maxDistance;
        this.maxRadius = maxRadius;
        this.offset = offset;
        this.particleList = particleList;
        this.boundEntity = boundEntity;
        shapeMeta = new ShapeMeta().setRadius(shape.getRadius())
                .setPoints(shape.getPoints())
                .setLength(shape.getLength())
                .setRotationSpeed(shape.getRotationSpeed());
    }
    public ParticleShape(GenesisShape shape, double maxRadius, double maxDistance, List<Particle> particleList, Location boundLocation) {
        this(shape,maxRadius,maxDistance,null,particleList, boundLocation);
    }
    public ParticleShape(GenesisShape shape, double maxRadius, double maxDistance, Vector offset, List<Particle> particleList, Location boundLocation) {
        this.x = shape.getX();
        this.y = shape.getY();
        this.z = shape.getZ();
        this.maxDistance = maxDistance;
        this.maxRadius = maxRadius;
        this.offset = offset;
        this.particleList = particleList;
        this.boundLocation = boundLocation;
        shapeMeta = new ShapeMeta().setRadius(shape.getRadius())
                .setPoints(shape.getPoints())
                .setLength(shape.getLength());
    }

    public ParticleShape(double maxRadius, double maxDistance, Vector offset, String z, String y, String x, List<Particle> particleList, Entity boundEntity) {
        this.maxRadius = maxRadius;
        this.maxDistance = maxDistance;
        this.offset = offset;
        this.z = z;
        this.y = y;
        this.x = x;
        this.particleList = particleList;
        this.boundEntity = boundEntity;
    }

    public ParticleShape(double maxRadius, double maxDistance, Vector offset, String z, String y, String x, List<Particle> particleList, Location boundLocation) {
        this.maxRadius = maxRadius;
        this.maxDistance = maxDistance;
        this.offset = offset;
        this.z = z;
        this.y = y;
        this.x = x;
        this.particleList = particleList;
        this.boundLocation = boundLocation;
    }

    public Entity getBoundEntity() {
        return boundEntity;
    }

    public void setBoundEntity(Entity boundEntity) {
        this.boundEntity = boundEntity;
    }

    public Location getBoundLocation() {
        return boundLocation;
    }

    public void setBoundLocation(Location boundLocation) {
        this.boundLocation = boundLocation;
    }

    public void setShapeMeta(ShapeMeta shapeMeta) {
        this.shapeMeta = shapeMeta;
    }

    public double getMaxRadius() {
        return maxRadius;
    }

    public ShapeMeta getShapeMeta() {
        return shapeMeta;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public Vector getOffset() {
        return offset;
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

    public List<Particle> getParticleList() {
        return particleList;
    }
}
