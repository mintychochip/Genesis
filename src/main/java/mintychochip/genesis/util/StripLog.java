package mintychochip.genesis.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;

public class StripLog implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(final PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR || event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack itemInMainHand = inventory.getItemInMainHand();
        ItemStack itemInOffHand = inventory.getItemInOffHand();
        if (itemInMainHand.getType() == Material.AIR || itemInOffHand.getType() == Material.AIR) {
            return;
        }
        if (canStrip(itemInMainHand, itemInOffHand) || canStrip(itemInOffHand, itemInMainHand)) { //checks the first case where the player holds axe in main, and the log is in offhand
            ItemStack logs = itemStackWithString(itemInMainHand, itemInOffHand, "LOG");
            logs.setType(Material.valueOf("STRIPPED_" + logs.getType()));
            ItemStack axe = itemStackWithString(itemInMainHand, itemInOffHand, "AXE");
            if (axe.getItemMeta() instanceof Damageable damageable) {
                int enchantLevel = damageable.getEnchantLevel(Enchantment.DURABILITY);
                double damage = logs.getAmount();
                if (enchantLevel > 0) {
                    damage /= enchantLevel;
                }
                damageable.setDamage(damageable.getDamage() - Math.round((float) damage));
            }
        }
    }

    public boolean canStrip(ItemStack mainHand, ItemStack offHand) {
        String main = mainHand.getType().toString();
        String off = offHand.getType().toString();
        if (off.contains("STRIPPED")) {
            return false;
        }
        return off.contains("LOG") && main.contains("AXE");
    }

    public ItemStack itemStackWithString(ItemStack mainHand, ItemStack offHand, String match) {
        return mainHand.getType().toString().contains(match.toUpperCase()) ? mainHand : offHand;
    }
}
