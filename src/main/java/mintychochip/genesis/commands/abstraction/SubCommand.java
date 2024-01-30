package mintychochip.genesis.commands.abstraction;

import org.bukkit.entity.Player;

public interface SubCommand { //port to gen
     public boolean execute(String[] strings, Player sender);
}
