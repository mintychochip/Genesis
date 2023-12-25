package mintychochip.genesis.particle;

public enum GenesisShape {
    SPHERE ("radius * sin(i/10*pi)",
            "radius * sin((i%10)*pi) * cos(i/10*pi)",
            "radius * cos((i%10)*pi) * cos(i/10*pi)",
            Math.PI * 15,
            2, 300),
    CIRCLE ("radius * sin(i)", "radius * cos(i)", "0", Math.PI * 2, 2, 200);
    private final String x;
    private final String y;
    private final String z;
    private final double length;
    private final double radius;
    private final int points;
    GenesisShape(String x, String y, String z, double length, double radius,int points) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.length = length;
        this.radius = radius;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public double getRadius() {
        return radius;
    }

    public double getLength() {
        return length;
    }

    public String getY() {
        return y;
    }

    public String getZ() {
        return z;
    }

    public String getX() {
        return x;
    }
}
