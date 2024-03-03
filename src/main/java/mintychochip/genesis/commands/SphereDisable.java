package mintychochip.genesis.commands;

import mintychochip.genesis.commands.abstraction.GenericCommandObject;
import mintychochip.genesis.commands.abstraction.SubCommand;
import org.bukkit.entity.Player;

public class SphereDisable extends GenericCommandObject implements SubCommand {
    public SphereDisable(String executor, String description) {
        super(executor, description);
    }

    @Override
    public boolean execute(String[] strings, Player sender) {
        return false;
    }
}
