package mintychochip.genesis;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GenesisCraftEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    private final ItemStack result;

    public GenesisCraftEvent(Player player, ItemStack result) {
        this.player = player;
        this.result = result;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getCrafter() {
        return player;
    }

    public ItemStack getResult() {
        return result;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
