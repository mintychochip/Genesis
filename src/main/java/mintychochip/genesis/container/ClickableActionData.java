package mintychochip.genesis.container;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.container.items.actions.ActionPacket;
import mintychochip.genesis.container.items.actions.EventAction;
import mintychochip.genesis.container.items.interfaces.Embeddable;
import org.bukkit.NamespacedKey;

import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickableActionData implements Embeddable {
    private final Map<String,ActionPacket> packets = new HashMap<>();
    private static final NamespacedKey key = Genesis.getKey("clickable");

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
    public boolean hasNoActions() {
        return packets.isEmpty();
    }
}
