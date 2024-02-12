package mintychochip.genesis.container;

public class DropTableSettings {
    private double dropRate = 0.5;
    private int minCount = 0;
    private int maxCount = 1;
    private boolean lootingApplicable = false;
    private boolean requiresPlayerKill = false;

    public DropTableSettings(double dropRate, int minCount, int maxCount, boolean lootingApplicable, boolean requiresPlayerKill) {
        this.dropRate = dropRate;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.lootingApplicable = lootingApplicable;
        this.requiresPlayerKill = requiresPlayerKill;
    }
    public DropTableSettings() {

    }
    public DropTableSettings copy(DropTableSettings settings) {
        this.dropRate = settings.getDropRate();
        this.minCount = settings.getMinCount();
        this.maxCount = settings.getMaxCount();
        this.lootingApplicable = settings.isLootingApplicable();
        this.requiresPlayerKill = settings.isRequiresPlayerKill();
        return this;
    }

    public double getDropRate() {
        return dropRate;
    }

    public void setDropRate(double dropRate) {
        this.dropRate = dropRate;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public boolean isLootingApplicable() {
        return lootingApplicable;
    }

    public void setLootingApplicable(boolean lootingApplicable) {
        this.lootingApplicable = lootingApplicable;
    }

    public boolean isRequiresPlayerKill() {
        return requiresPlayerKill;
    }

    public void setRequiresPlayerKill(boolean requiresPlayerKill) {
        this.requiresPlayerKill = requiresPlayerKill;
    }

    public String toString() {
        return dropRate + " " + minCount + " " + maxCount + " " + lootingApplicable;
    }
}
