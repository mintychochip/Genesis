package mintychochip.genesis.util;

import com.fathzer.soft.javaluator.StaticVariableSet;
import mintychochip.genesis.Genesis;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Random;

public class MathUtil {

    private final Random random = new Random();

    public static boolean finishedDuration(double duration, long start) {
        long l = (System.currentTimeMillis() - start) / 1000;
        return l >= duration;
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

    public static double elapsedSeconds(long timestamp) {
        return (double) (System.currentTimeMillis() - timestamp) / 1000;
    }

    public static double remainingSeconds(long timestamp, double duration) {
        return duration - elapsedSeconds(timestamp);
    }

    public static boolean timeOver(long timestamp, double duration) {
        return elapsedSeconds(timestamp) >= duration;
    }

    public static double evaluateFunction(String function, StaticVariableSet<Double> variableSet) {
        return Genesis.getParser().evaluate(function, variableSet);
    }

    public Random getRandom() {
        return random;
    }

}
