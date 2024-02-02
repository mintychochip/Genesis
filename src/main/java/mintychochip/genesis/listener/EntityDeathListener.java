package mintychochip.genesis.listener;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.config.GenesisRegistry;
import mintychochip.genesis.container.DropTableSettings;
import mintychochip.genesis.container.GenesisDropTableEntry;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class EntityDeathListener implements Listener {

    @EventHandler
    private void onEntityDeathToPlayer(final EntityDeathEvent event) {
        if (GenesisRegistry.getDropTableEntries().containsKey(event.getEntityType())) {
            for (GenesisDropTableEntry genesisDropTableEntry : GenesisRegistry.getDropTableEntries().get(event.getEntityType())) {
                Bukkit.broadcastMessage(genesisDropTableEntry.getDropTableSettings().toString());
            }
            for (GenesisDropTableEntry genesisDropTableEntry : GenesisRegistry.getDropTableEntries().get(event.getEntityType())) {
                ItemStack itemStack = genesisDropTableEntry.getItemStack();
                Random random = Genesis.getMath().getRandom();
                DropTableSettings dropTableSettings = genesisDropTableEntry.getDropTableSettings();
                Bukkit.broadcastMessage(dropTableSettings.toString());
                int count = random.nextInt(dropTableSettings.getMaxCount() - dropTableSettings.getMinCount()) + dropTableSettings.getMinCount();
                double dropChance = random.nextDouble();
                if (dropChance < dropTableSettings.getDropRate()) {
                    World world = event.getEntity().getLocation().getWorld();
                    itemStack.setAmount(count);
                    Item item = world.dropItemNaturally(event.getEntity().getLocation(), itemStack);
                    item.setCustomName(itemStack.getItemMeta().getDisplayName());
                    item.setCustomNameVisible(true);
                }
            }
        }
    }
}
