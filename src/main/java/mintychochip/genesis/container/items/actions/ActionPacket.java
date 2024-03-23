package mintychochip.genesis.container.items.actions;

import java.io.Serializable;

public class ActionPacket implements Serializable {
    private final EventAction EventAction;

    private final String Value; //change impl later
    public ActionPacket(EventAction eventAction, String value) {
        this.EventAction = eventAction;
        this.Value = value;
    }

    public EventAction getEventAction() {
        return EventAction;
    }

    public String getValue() {
        return Value;
    }
}
