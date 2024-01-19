package mintychochip.genesis.listener;

import mintychochip.genesis.Genesis;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class BindListener implements Listener {

    @EventHandler
    public void onBindBookInteract(final PlayerInteractEvent event) {
        PlayerInventory inventory = event.getPlayer().getInventory();
        UUID user = event.getPlayer().getUniqueId();
        UUID uuidFromMainHand = getUUIDFromItemStack(inventory.getItemInMainHand());
        UUID uuidFromOffHand = getUUIDFromItemStack(inventory.getItemInOffHand());
        if (!user.equals(uuidFromMainHand) && uuidFromMainHand != null) {
            closeInventory(event.getPlayer(), uuidFromMainHand);
            event.setCancelled(true);
            return;
        }
        if (!user.equals(uuidFromOffHand)) {
            closeInventory(event.getPlayer(), uuidFromOffHand);
            event.setCancelled(true);
        }
    }

    public void closeInventory(Player player, UUID uuid) {
        Bukkit.broadcastMessage(ChatColor.RED + "This book is bound to: " + uuid.toString());
        player.closeInventory();
    }

    public UUID getUUIDFromItemStack(ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR) {
            return null;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return null;
        }
        String owner = itemStack.getItemMeta().getPersistentDataContainer().get(Genesis.getKey("player"), PersistentDataType.STRING);
        if (owner == null) {
            return null;
        }
        Player player = Bukkit.getPlayer(owner);
        return player == null ? Bukkit.getOfflinePlayer(owner).getUniqueId() : player.getUniqueId();
    }
}
