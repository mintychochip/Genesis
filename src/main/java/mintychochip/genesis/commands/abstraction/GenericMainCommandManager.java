package mintychochip.genesis.commands.abstraction;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GenericMainCommandManager extends GenericCommandManager implements CommandExecutor {
    public GenericMainCommandManager(String executor, String description) {
        super(executor, description);
    } //command managers at 0

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length < depth) {
            return false;
        }
        if (commandSender instanceof Player player) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand instanceof GenericCommandObject gco) {
                    if (gco.getExecutor().equalsIgnoreCase(strings[depth - 1])) {
                        subCommand.execute(strings, player);
                    }
                }
            }
        }
        return false;
    }
}
