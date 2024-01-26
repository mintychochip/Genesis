package mintychochip.genesis.flags;

import mintychochip.genesis.Genesis;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface Bindable {

    default ItemStack addBind(ItemStack itemStack, String playerName) { //can make better
        Genesis.getKeys().assignStringToItemStack("player",playerName,itemStack);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();
        lore.add(ChatColor.DARK_GRAY + "This item belongs to: " + ChatColor.GOLD + playerName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
