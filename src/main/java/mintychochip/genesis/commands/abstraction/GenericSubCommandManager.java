package mintychochip.genesis.commands.abstraction;

import org.bukkit.entity.Player;

public class GenericSubCommandManager extends GenericCommandManager implements SubCommand {
    public GenericSubCommandManager(String executor, String description, int depth, String preceding) {
        super(executor, description, depth);
        this.preceding += preceding + " " + executor;
    }

    @Override
    public boolean execute(String[] strings, Player sender) {
        if (strings.length < depth) {
            return false;
        }
        for (SubCommand subCommand : subCommands) {
            if (subCommand instanceof GenericCommandObject gco) {
                if (gco.getExecutor().equals(strings[depth - 1])) {
                    if(subCommand.execute(strings, sender)) {
                        return true;
                    }
                    for (String s : menu) {
                        sender.sendMessage(s);
                    }
                    return false;
                }
            }
        }
        return false;
    }

}
