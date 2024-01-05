package mintychochip.genesis.particle;

public enum GenesisShape {
    SPHERE ("sin(i/10*pi)",
            "sin((i%10)*pi) * cos(i/10*pi)",
            "cos((i%10)*pi) * cos(i/10*pi)",
            Math.PI * 15,
            2, 200, 7200),
    CIRCLE ("sin(i)", "cos(i)", "0", Math.PI * 2, 2, 200, 7200),
    TEST("sin(i)","sin(2*i)","cos(i)",Math.PI * 15, 2, 200, 7200),
    ROSE("sin(2*i)*cos(i)","sin(2*i)*sin(i)","cos(2*i)",Math.PI*2,2,200,7200),
    ANOTHER("(9-1) * cos(i) + cos((9-1)*i)","(9-1) * sin(i) + sin((9-1)*i)","0",Math.PI*2,2,200,7200),
    LIS("sin(i*15)","sin(10*i)","sin(180*i)",Math.PI*2,2,200,7200);
    private final String x;
    private final String y;
    private final String z;
    private final double length;
    private final double radius;
    private final int points;

    private final double rotationSpeed;
    GenesisShape(String x, String y, String z, double length, double radius,int points, double rotationSpeed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.length = length;
        this.radius = radius;
        this.points = points;
        this.rotationSpeed = rotationSpeed;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
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
