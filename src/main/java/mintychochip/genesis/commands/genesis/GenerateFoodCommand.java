package mintychochip.genesis.commands.genesis;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.commands.abstraction.GenericCommandObject;
import mintychochip.genesis.commands.abstraction.SubCommand;
import mintychochip.genesis.config.TestConfig;
import mintychochip.genesis.container.ItemData;
import mintychochip.genesis.container.items.AbstractItem;
import mintychochip.genesis.container.items.actions.ActionPacket;
import mintychochip.genesis.container.items.actions.EventAction;
import mintychochip.genesis.container.items.interfaces.Embeddable;
import org.bukkit.entity.Player;

public class GenerateFoodCommand extends GenericCommandObject implements SubCommand {
    public GenerateFoodCommand(String executor, String description) {
        super(executor, description);
    }

    @Override
    public boolean execute(String[] strings, Player sender) {
        TestConfig testConfig = Genesis.getGenesisConfigManager().getTestConfig();
        AbstractItem.EmbeddedDataBuilder classic = new AbstractItem.EmbeddedDataBuilder(Genesis.getInstance(), testConfig.getBottle(), false, "CLASSIC", new ItemData()).addConsumeEvent("drink",new ActionPacket(EventAction.EXECUTE_COMMAND,"playsound minecraft:dreamy.buss voice @a"));
        sender.getInventory().addItem(classic.defaultBuild().getItemStack());
        return true;
    }
}
