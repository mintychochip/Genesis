package mintychochip.genesis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MappingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player player) {
            if(strings.length < 1) {
                player.sendMessage(Genesis.getItemManager().getItemMappings().toString());
                return true;
            }
            if(strings[0].equalsIgnoreCase("results")) {
                player.sendMessage(Genesis.getItemManager().getResultMappings().toString());
            }
        }
        return true;
    }
}
