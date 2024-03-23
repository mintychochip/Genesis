package mintychochip.genesis.container.items;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.container.items.actions.ActionPacket;
import mintychochip.genesis.container.items.interfaces.Embeddable;
import mintychochip.genesis.events.ActionEventType;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public class ActionData implements Embeddable {
    private final Map<String, ActionPacket> packets = new HashMap<>();
    private final NamespacedKey key;

    private final String simpleKey;
    public ActionData(String simpleKey) {
        this.simpleKey = simpleKey;
        this.key = Genesis.getKey(simpleKey);
    }
    public ActionData(ActionEventType actionEventType) {
        this.simpleKey = actionEventType.getSimpleKey();
        this.key = actionEventType.getKey();
    }

    public Map<String, ActionPacket> getPackets() {
        return packets;
    }

    public boolean addActionPacket(String key, ActionPacket actionPacket) {
        if (actionPacket == null) {
            return false;
        }
        packets.put(key,actionPacket);
        return true;
    }
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public String getSimpleKey() {
        return simpleKey;
    }

    @Override
    public String getType() {
        return Embeddable.super.getType();
    }

    public boolean hasNoActions() {
        return packets.isEmpty();
    }
}
