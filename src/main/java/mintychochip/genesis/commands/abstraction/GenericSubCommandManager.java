package mintychochip.genesis.commands.abstraction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GenericSubCommandManager extends GenericCommandManager implements SubCommand {
    public GenericSubCommandManager(String executor, String description, int depth) {
        super(executor, description, depth);
    } //instance, ollivanders (1) books (2) set
    @Override
    public boolean execute(String[] args, Player sender) {
        if(args.length < depth) {
            return false;
        }
        for (SubCommand subCommand : subCommands) {
            if(subCommand instanceof GenericCommandObject gco) {
                if(gco.getExecutor().equals(args[depth - 1])) {
                    return subCommand.execute(args,sender);
                }
            }
        }
        return false;
    }

}
