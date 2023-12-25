package mintychochip.genesis.container;

import java.io.Serializable;

public class ItemData implements Serializable {

    protected String displayName;

    public ItemData setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }
}
