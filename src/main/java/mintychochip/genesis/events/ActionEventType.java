package mintychochip.genesis.events;

import mintychochip.genesis.Genesis;
import org.bukkit.NamespacedKey;

public enum ActionEventType {
    CLICK("clickable"),
    CONSUME("consumable");

    private final String key;

    ActionEventType(String key) {
        this.key = key;
    }
    public String getSimpleKey() {
        return this.key;
    }
    public NamespacedKey getKey() {
        return Genesis.getKey(key);
    }
}
