package mintychochip.genesis.particle;

public class ParticlePackage {
    private final ParticleShape particleShape;

    private final long delay;

    private final long interval;

    public ParticlePackage(ParticleShape particleShape, long delay, long interval) {
        this.particleShape = particleShape;
        this.delay = delay;
        this.interval = interval;
    }

    public ParticleShape getParticleShape() {
        return particleShape;
    }

    public long getDelay() {
        return delay;
    }

    public long getInterval() {
        return interval;
    }
}
