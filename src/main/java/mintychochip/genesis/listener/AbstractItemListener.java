package mintychochip.genesis.listener;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.GenesisCraftEvent;
import mintychochip.genesis.particle.*;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AbstractItemListener implements Listener {

    //EventHandler
    public void playerInteractEvent(final PlayerInteractEvent event) {
        List<Particle> particleList = new ArrayList<>();
        particleList.add(Particle.REDSTONE);
        ParticleShape particleShape1 = new ParticleShape(GenesisShape.LIS, 2, 2, new Vector(0, 1, 0), particleList, event.getPlayer());
        ShapeMeta shapeMeta1 = particleShape1.getShapeMeta().addRotationAxis(1, 0, 0).addRotationAxis(0, 1, 0);
        particleShape1.setShapeMeta(shapeMeta1);
        ParticlePackage particlePackage = new ParticlePackage(particleShape1, 3L, 1L);
        ParticleEngine.render(particleShape1, 3L, 1L, event.getPlayer());
    }

    @EventHandler
    public void onPlayerDropOllivandersItem(final PlayerDropItemEvent event) {
        Item itemDrop = event.getItemDrop();
        ItemMeta itemMeta = itemDrop.getItemStack().getItemMeta();
        if (itemMeta == null) {
            return;
        }
        if (itemMeta.getPersistentDataContainer().has(Genesis.getKey("abstract"), PersistentDataType.BOOLEAN)) {
            itemDrop.setCustomName(itemMeta.getDisplayName());
            itemDrop.setCustomNameVisible(true);
        }
    }

    @EventHandler //thonking
    public void preventAbstractItemCraft(final PrepareItemCraftEvent event) {
        Set<NamespacedKey> customRecipes = Genesis.getGenesisConfigManager().getGenesisConfig().getCustomRecipes();
        if (event.getRecipe() instanceof Keyed keyed) {
            if (customRecipes.contains(keyed.getKey())) {
                return;
            }
            for (ItemStack content : event.getInventory().getContents()) {
                ItemMeta itemMeta = content.getItemMeta();
                if (itemMeta != null) {
                    if (itemMeta.getPersistentDataContainer().has(Genesis.getKey("abstract"), PersistentDataType.BOOLEAN)) {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                        return;
                    }
                }
            }
        }
    }
    //EventHandler
    @EventHandler
    public void onFurnaceSmelt(final FurnaceSmeltEvent event) {
        ItemMeta itemMeta = event.getSource().getItemMeta();
        Bukkit.broadcastMessage(itemMeta.toString());
        if(event.getResult().getItemMeta().getPersistentDataContainer().has(Genesis.getKey("abstract"),PersistentDataType.BOOLEAN)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onFurnaceStartSmelt(final FurnaceStartSmeltEvent event) {
        ItemStack result = event.getRecipe().getInput();
    }
    public void onInventoryMoveAbstractItemIntoFurnace(final InventoryMoveItemEvent event) {
        ItemStack item = event.getItem();
        if(!(event.getDestination().getType() == InventoryType.FURNACE)) {
            return;
        }
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) {
            return;
        }
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        if (persistentDataContainer.has(Genesis.getKey("abstract"), PersistentDataType.BOOLEAN)) {
            event.setCancelled(true);
        }
    }
    //EventHandler
    public void onPlayerMoveAbstractItemIntoFurnace(final InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player player)) {
            return;
        }
        if(event.getClickedInventory() == null) {
            return;
        }
        ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
        if(event.getClickedInventory().getType() != InventoryType.FURNACE) {
            return;
        }
        if(itemMeta == null) {
            return;
        }
        if(itemMeta.getPersistentDataContainer().has(Genesis.getKey("abstract"),PersistentDataType.BOOLEAN)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onCraftEvent(final CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        if (result.getType() == Material.AIR) {
            return;
        }
        if (event.getCursor() == null) {
            return;
        }
        if (event.getCursor().equals(result)) {
            return;
        }
        ItemMeta itemMeta = result.getItemMeta();
        if (itemMeta == null) {
            return;
        }
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        if (pdc.has(Genesis.getKey("abstract"), PersistentDataType.BOOLEAN)) {
            if (pdc.has(Genesis.getKey("bind"), PersistentDataType.BOOLEAN)) {
                String name = event.getWhoClicked().getName();
                pdc.set(Genesis.getKey("player"), PersistentDataType.STRING, name);
                List<String> lore = itemMeta.getLore();
                lore.add(ChatColor.DARK_GRAY + "This item belongs to: " + ChatColor.GOLD + name);
                itemMeta.setLore(lore);
                result.setItemMeta(itemMeta);
                event.getInventory().setResult(result);
            }
            Bukkit.getPluginManager().callEvent(new GenesisCraftEvent((Player) event.getWhoClicked(), result));
        }
    }

}
