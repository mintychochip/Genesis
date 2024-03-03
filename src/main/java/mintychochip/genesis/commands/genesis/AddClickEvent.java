package mintychochip.genesis.commands.genesis;

import mintychochip.genesis.commands.abstraction.GenericCommandObject;
import mintychochip.genesis.commands.abstraction.SubCommand;
import org.bukkit.entity.Player;

public class AddClickEvent extends GenericCommandObject implements SubCommand {
    public AddClickEvent(String executor, String description) {
        super(executor, description);
    }

    @Override
    public boolean execute(String[] strings, Player sender) {
        if(strings.length < depth) {
            return false;
        }

        return false;
    }
}
