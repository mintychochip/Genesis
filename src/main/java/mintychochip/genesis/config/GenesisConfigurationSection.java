package mintychochip.genesis.config;

import mintychochip.genesis.util.EnumUtil;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class GenesisConfigurationSection {

    private ConfigurationSection configurationSection;

    public GenesisConfigurationSection(ConfigurationSection configurationSection) {
        this.configurationSection = configurationSection;
    }

    public ConfigurationSection getConfigurationSection() {
        return configurationSection;
    }
    public <E extends Enum<E>> E enumFromSection(Class<E> enumClass, String marker) {
        String unknown = configurationSection.getString(marker);
        if (unknown == null) {
            return Enum.valueOf(enumClass, "DEFAULT");
        }
        if (!EnumUtil.isInEnum(enumClass, unknown.toUpperCase())) {
            throw new IllegalArgumentException();
        }
        return Enum.valueOf(enumClass, unknown.toUpperCase());
    }
    public double getDouble(String marker) {
        return configurationSection.getDouble(marker);
    }
    public int getInt(String marker) {
        return configurationSection.getString(marker) != null ? configurationSection.getInt(marker) : -1;
    }

    public List<String> getStringList(String marker) {
        return configurationSection.getStringList(marker);
    }

    public String getString(String marker) {
        return configurationSection.getString(marker);
    }
}
