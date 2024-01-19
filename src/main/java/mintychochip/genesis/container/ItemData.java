package mintychochip.genesis.container;

import java.io.Serializable;

public class ItemData implements Serializable {

    protected String displayName;
    protected String key;

    public ItemData(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ItemData setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }


}
