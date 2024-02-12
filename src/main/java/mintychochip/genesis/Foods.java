package mintychochip.genesis;

import mintychochip.genesis.config.TestConfig;
import mintychochip.genesis.container.AbstractItem;
import mintychochip.genesis.container.ItemData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;

public class Foods implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            TestConfig testConfig = Genesis.getGenesisConfigManager().getTestConfig();
            AbstractItem build = new AbstractItem.EmbeddedDataBuilder(Genesis.getInstance(), testConfig.getSection().getConfigurationSection(strings[0]), false, "CLASSIC", new ItemData()).defaultBuild();
            player.getInventory().addItem(build.getItemStack());
        }
        return false;
    }
}
