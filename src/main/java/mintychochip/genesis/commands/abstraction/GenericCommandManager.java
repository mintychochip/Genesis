package mintychochip.genesis.commands.abstraction;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericCommandManager extends GenericCommandObject { //command managers at 0
    protected List<SubCommand> subCommands = new ArrayList<>();
    protected List<String> menu = new ArrayList<>();
    protected Map<String, GenericSubCommandManager> subCommandManagers = new HashMap<>();
    protected String preceding = ""; //contains the preceding string at given depth can probably deprecate depth after using this

    public GenericCommandManager(String executor, String description, int depth) {
        super(executor, description, depth);
    }
    public GenericCommandManager(String executor, String description) {
        this(executor, description, 1);
    }

    public boolean hasCorrectDepth(String[] strings) {
        return !(strings.length < depth);
    }

    public GenericCommandManager addSubCommand(SubCommand subCommand) {
        if (subCommand instanceof GenericCommandObject gco) {
            gco.setDepth(depth + 1);
        }
        subCommands.add(subCommand);
        return this;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public boolean isASubCommand(String command) {
        for (SubCommand subCommand : subCommands) {
            if (subCommand instanceof GenericCommandObject gco) {
                if (gco.getExecutor().equalsIgnoreCase(command)) {
                    return true;
                }
            }
        }
        return false;
    }

    public GenericSubCommandManager instantiateSubCommandManager(String executor, String description) {
        GenericSubCommandManager genericSubCommandManager = new GenericSubCommandManager(executor, description, depth + 1, preceding);
        this.subCommands.add(genericSubCommandManager);
        subCommandManagers.put(executor, genericSubCommandManager);
        return genericSubCommandManager;
    }
}
