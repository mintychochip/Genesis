package mintychochip.genesis.commands.abstraction;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericCommandManager extends GenericCommandObject { //command managers at 0
    protected List<SubCommand> subCommands = new ArrayList<>();

    protected Map<String,GenericSubCommandManager> subCommandManagers = new HashMap<>();

    public GenericCommandManager(String executor, String description, int depth) {
        super(executor, description, depth);
    }

    public GenericCommandManager(String executor, String description) {
        super(executor, description);
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
            if(subCommand instanceof GenericCommandObject gco) {
                if(gco.getExecutor().equalsIgnoreCase(command)) {
                    return true;
                }
            }
        }
        return false;
    }

    public GenericSubCommandManager instantiateSubCommandManager(String executor, String description) {
        GenericSubCommandManager genericSubCommandManager = new GenericSubCommandManager(executor, description, depth + 1);
        this.subCommands.add(genericSubCommandManager);
        subCommandManagers.put(executor,genericSubCommandManager);
        return genericSubCommandManager;
    }


}
