package mintychochip.genesis;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;

public class GenesisCraftEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;

    private ItemStack result;

    public GenesisCraftEvent(Player player, ItemStack result) {
        this.player = player;
        this.result = result;
    }
    public Player getCrafter() {
        return player;
    }

    public ItemStack getResult() {
        return result;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
