package mintychochip.genesis.listener;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.container.ItemData;
import mintychochip.genesis.util.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.Set;

public class FlagListener implements Listener {
    private final double fallDamageCoefficient = 0.5;
    private final double flatRange = 2;
    private final double vertRange = 1;
    @EventHandler
    public void onPlayerFallOnLeaves(final EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (event.getDamage() == 0) {
            return;
        }
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }
        double damage = event.getDamage() * fallDamageCoefficient;
        Location location = player.getLocation();
        for (double i = -vertRange; i <= vertRange; i++) {
            for (double j = -flatRange; j <= flatRange; j++) {
                for (double k = -flatRange; k <= flatRange; k++) {
                    if (location.add(i, j, k).getBlock().getType().toString().contains("LEAVES")) {
                        if (player.getHealth() == 1) {
                            event.setDamage(0);
                        } else if (damage > player.getHealth()) {
                            event.setDamage(player.getHealth() - 1);
                        } else {
                            event.setDamage(damage);
                        }
                        return;
                    }
                    location.subtract(i, j, k);
                }
            }
        }
    }

    @EventHandler
    private void onPlayerInteract(final PlayerInteractEvent event) {
        PlayerInventory inventory = event.getPlayer().getInventory();
        ItemMeta itemMeta = inventory.getItemInMainHand().getItemMeta();
        if(itemMeta == null) {
            return;
        }
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        Set<NamespacedKey> keys = persistentDataContainer.getKeys();
        Bukkit.broadcastMessage(keys.toString());
        if(persistentDataContainer.has(Genesis.getKey("component"), PersistentDataType.BYTE_ARRAY)) {
            byte[] items = persistentDataContainer.get(Genesis.getKey("component"), PersistentDataType.BYTE_ARRAY);
            try {
                ItemData deserialize = Serializer.deserialize(items);
                String string = deserialize.toString();
                Bukkit.broadcastMessage(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
