package mintychochip.genesis.particle;

import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ShapeMeta {

    private double radiusIncrement;
    private double radius;
    private double length;
    private double rotationSpeed;
    private int points;
    private List<Vector> rotationAxes = null;
    private double degreesRotated = 0;

    public ShapeMeta addRotationAxis(double x, double y, double z) {
        addRotationAxis(new Vector(x, y, z));
        return this;
    }

    public ShapeMeta incrementRadius() {
        radius += radiusIncrement;
        return this;
    }

    public ShapeMeta addRotationAxis(Vector vector) {
        if (rotationAxes == null) {
            rotationAxes = new ArrayList<>();
        }
        rotationAxes.add(vector);
        return this;
    }

    public double getRadiusIncrement() {
        return radiusIncrement;
    }

    public ShapeMeta setRadiusIncrement(double radiusIncrement) {
        this.radiusIncrement = radiusIncrement;
        return this;
    }

    public double rotate(double rotation) {
        degreesRotated += rotation;
        return degreesRotated;
    }

    public double getDegreesRotated() {
        return degreesRotated;
    }

    public void setDegreesRotated(double degreesRotated) {
        this.degreesRotated = degreesRotated;
    }

    public double getRadius() {
        return radius;
    }

    public ShapeMeta setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public double getLength() {
        return length;
    }

    public ShapeMeta setLength(double length) {
        this.length = length;
        return this;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public ShapeMeta setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public ShapeMeta setPoints(int points) {
        this.points = points;
        return this;
    }

    public List<Vector> getRotationAxes() {
        return rotationAxes;
    }

    public ShapeMeta setRotationAxes(List<Vector> rotationAxes) {
        this.rotationAxes = rotationAxes;
        return this;
    }
}
