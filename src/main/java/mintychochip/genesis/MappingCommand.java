package mintychochip.genesis;

import mintychochip.genesis.manager.RecipeRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MappingCommand implements CommandExecutor {

    private final RecipeRegistry recipeRegistry;
    public MappingCommand(RecipeRegistry recipeRegistry) {
        this.recipeRegistry = recipeRegistry;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player player) {
            if(strings.length < 1) {
                player.sendMessage(recipeRegistry.getItemMappings().toString());
                return true;
            }
            if(strings[0].equalsIgnoreCase("results")) {
                player.sendMessage(recipeRegistry.getResultMappings().toString());
            }
        }
        return true;
    }
}
