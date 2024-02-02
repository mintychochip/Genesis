package mintychochip.genesis.chestclaim;

import mintychochip.genesis.commands.abstraction.GenericCommandObject;
import mintychochip.genesis.commands.abstraction.SubCommand;
import org.bukkit.entity.Player;

public class ChestClaim extends GenericCommandObject implements SubCommand {

    public ChestClaim(String executor, String description) {
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
