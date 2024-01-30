package mintychochip.genesis.commands.abstraction;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenericMainCommandManager extends GenericCommandManager implements CommandExecutor,TabCompleter {
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

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> str = new ArrayList<>();
        int tabCompletionDepth = strings.length;
        List<SubCommand> commands = subCommands;
        if (strings[tabCompletionDepth - 1].equalsIgnoreCase("")) {
            if(tabCompletionDepth != 1) {
                for (int i = 0; i < tabCompletionDepth - 1; i++) {
                    GenericSubCommandManager genericSubCommandManager = subCommandManagers.get(strings[i]);
                    if(genericSubCommandManager == null) {
                        return null;
                    }
                    commands = genericSubCommandManager.getSubCommands();
                }
            }
            for (SubCommand subCommand : commands) {
                if (subCommand instanceof GenericCommandObject gco) {
                    str.add(gco.getExecutor());
                }
            }
        }
        return str;
    }

}
