package mintychochip.genesis.util;

import mintychochip.genesis.Genesis;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathUtil {

    private final Random random = new Random();

    public Random getRandom() {
        return random;
    }

    public static double roundToDecimals(double d, int c) {
        int temp = (int) (d * java.lang.Math.pow(10, c));
        return ((double) temp) / java.lang.Math.pow(10, c);
    }
    public static Vector rotateFunction(Vector v, double originalYaw, double originalPitch) {
        double v1 = originalPitch / 180.0 * Math.PI;
        double v2 = originalYaw / 180.0 * Math.PI;
        v = rotateAboutX(v, v1);
        v = rotateAboutY(v, -v2);
        return v;
    }
    public static Vector rotateFunction(Vector v, Location loc) {
        double yawR = loc.getYaw() / 180.0 * Math.PI;
        double pitchR = loc.getPitch() / 180.0 * Math.PI;
        v = rotateAboutX(v, pitchR);
        v = rotateAboutY(v, -yawR);
        return v;
    }

    public static Vector rotateAboutX(Vector v, double a) {
        double yPrime = Math.cos(a) * v.getY() - Math.sin(a) * v.getZ();
        double zPrime = Math.sin(a) * v.getY() + Math.cos(a) * v.getZ();
        return v.setY(yPrime).setZ(zPrime);
    }

    public static Vector rotateAboutY(Vector v, double a) {
        double xPrime = Math.cos(a) * v.getX() + Math.sin(a) * v.getZ();
        double zPrime = -Math.sin(a) * v.getX() + Math.cos(a) * v.getZ();
        return v.setX(xPrime).setZ(zPrime);
    }

    public static double perimeter(double radius) {
        return radius * 2 * Math.PI;
    }

}
