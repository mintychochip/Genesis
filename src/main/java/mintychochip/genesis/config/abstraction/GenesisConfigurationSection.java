package mintychochip.genesis.config.abstraction;

import mintychochip.genesis.util.EnumUtil;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Set;

public class GenesisConfigurationSection {

    protected ConfigurationSection main;

    protected String path;

    public GenesisConfigurationSection(ConfigurationSection configurationSection, String path) {
        this.main = configurationSection;
        this.path = path;
    }

    public boolean isNull() {
        return this.main == null;
    }

    public Set<String> getKeys(boolean b) {
        return main.getKeys(b);
    }

    public <E extends Enum<E>> E enumFromSection(Class<E> enumClass, String marker) {
        String unknown = main.getString(marker);
        if (unknown == null) {
            return Enum.valueOf(enumClass, "DEFAULT");
        }
        if (!EnumUtil.isInEnum(enumClass, unknown.toUpperCase())) {
            throw new IllegalArgumentException();
        }
        return Enum.valueOf(enumClass, unknown.toUpperCase());
    }

    public String getPath() {
        return path;
    }

    public long getLong(String marker) {
        return main.getLong(marker);
    }

    public boolean getBoolean(String marker) {
        return main.getBoolean(marker);
    }

    public GenesisConfigurationSection getConfigurationSection(String path) {
        return new GenesisConfigurationSection(main.getConfigurationSection(path), path);
    }

    public double getDouble(String marker) {
        return main.getDouble(marker);
    }

    public int getInt(String marker) {
        return main.getString(marker) != null ? main.getInt(marker) : -1;
    }

    public List<String> getStringList(String marker) {
        return main.getStringList(marker);
    }

    public String getString(String marker) {
        return main.getString(marker);
    }
}
