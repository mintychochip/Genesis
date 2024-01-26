package mintychochip.genesis.commands.abstraction;

import java.util.ArrayList;
import java.util.List;

public class GenericCommandManager extends GenericCommandObject { //command managers at 0
    protected List<SubCommand> subCommands = new ArrayList<>();

    public GenericCommandManager(String executor, String description, int depth) {
        super(executor, description, depth);
    }

    public GenericCommandManager(String executor, String description) {
        super(executor, description);
    }

    public boolean hasCorrectDepth(String[] args) {
        return !(args.length < depth);
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

    public GenericSubCommandManager instantiateSubCommandManager(String executor, String description) {
        GenericSubCommandManager genericSubCommandManager = new GenericSubCommandManager(executor, description, depth + 1);
        this.subCommands.add(genericSubCommandManager);
        return genericSubCommandManager;
    }
}
