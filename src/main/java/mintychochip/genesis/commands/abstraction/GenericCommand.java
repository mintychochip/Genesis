package mintychochip.genesis.commands.abstraction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GenericCommand extends GenericCommandObject {
    protected Map<Integer, Set<String>> stringsAtDepth = new HashMap<>();
    private int increment = 0;

    public GenericCommand(String executor, String description, Set<String> strings) {
        super(executor, description);
        addSetToStrings(strings);
    }

    public void addSetToStrings(Set<String> strings) {
        stringsAtDepth.put(increment++, strings);
    }

    public Map<Integer, Set<String>> getStringsAtDepth() {
        return stringsAtDepth;
    }
}
