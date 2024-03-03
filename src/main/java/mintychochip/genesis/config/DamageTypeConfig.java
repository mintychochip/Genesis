package mintychochip.genesis.config;

import mintychochip.genesis.config.abstraction.GenericConfig;
import mintychochip.genesis.config.abstraction.GenesisConfigurationSection;
import mintychochip.genesis.util.EnumUtil;
import mintychochip.genesis.util.GenesisConfigMarker;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class DamageTypeConfig extends GenericConfig {
    private GenesisConfigurationSection immunity = getMainConfigurationSection(GenesisConfigMarker.immunity);
    public DamageTypeConfig(String path, JavaPlugin plugin) {
        super(path, plugin);
    }

    private void loadImmunity() {
        if(!immunity.isNull()) {
            for (String key : immunity.getKeys(false)) {
                if(EnumUtil.isInEnum(EntityType.class,key.toUpperCase())) {
                    List<String> immunityTypes = immunity.getStringList(key);
                }
            }

        }
    }

}
