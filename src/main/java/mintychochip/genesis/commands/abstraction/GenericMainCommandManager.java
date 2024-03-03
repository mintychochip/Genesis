package mintychochip.genesis.commands.abstraction;

import mintychochip.genesis.Genesis;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenericMainCommandManager extends GenericCommandManager implements CommandExecutor, TabCompleter {

    public GenericMainCommandManager(String executor, String description) {
        super(executor, description);
        preceding = executor;
        new BukkitRunnable() {
            public void run() {
                for (SubCommand subCommand : subCommands) {
                    if (subCommand instanceof GenericCommandObject gco) {
                        menu.add("/" + executor + " " + gco.getExecutor());
                    }
                }

            }
        }.runTaskLater(Genesis.getInstance(), 10L);
    } //command managers at 0

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length < depth) {
            return false;
        }
        if (commandSender instanceof Player sender) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand instanceof GenericCommandObject gco) {
                    if (gco.getExecutor().equalsIgnoreCase(strings[depth - 1])) {
                        if (subCommand.execute(strings, sender)) {
                            return true;
                        }
                        for (String string : menu) {
                            sender.sendMessage(string);
                        }
                        return false;
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
        if (tabCompletionDepth != 1) {
            for (int i = 0; i < tabCompletionDepth - 1; i++) {
                GenericSubCommandManager genericSubCommandManager = subCommandManagers.get(strings[i]);
                if (genericSubCommandManager == null) {
                    for (SubCommand subCommand : commands) {
                        if (subCommand instanceof GenericCommand gc) {
                            if (gc.getExecutor().equals(strings[tabCompletionDepth - 2])) {
                                Set<String> strings1 = gc.getStringsAtDepth().get(0);
                                if (strings1 == null) {
                                    return null;
                                }
                                str.addAll(strings1);
                                return str;
                            }
                        }
                    }
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
        return str;
    }

}
