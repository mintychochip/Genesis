package mintychochip.genesis.builder;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.color.GenesisColor;
import mintychochip.genesis.color.GenesisTheme;
import mintychochip.genesis.container.AbstractItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Panda;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemBuilder {

    protected AbstractItem abstractItem;
    protected List<String> lore;

    protected GenesisTheme genesisTheme;

    public ItemBuilder(AbstractItem abstractItem, GenesisTheme genesisTheme) {
        this.abstractItem = abstractItem;
        this.genesisTheme = genesisTheme;
    }
    public ItemBuilder setRawDisplayName(String name) {
        abstractItem.getItemMeta().setDisplayName(name);
        return this;
    }
    public ItemBuilder setDisplayName(String name, char color) {
        abstractItem.getItemMeta().setDisplayName(GenesisColor.applyColor(genesisTheme.getHeaderColor(),color,name));
        return this;
    }
    public ItemBuilder setDisplayName(String name, ChatColor chatColor) {
        abstractItem.getItemMeta().setDisplayName(GenesisColor.applyColor(genesisTheme.getHeaderColor(),chatColor.getChar(),name));
        return this;
    }
    public ItemBuilder addBulletedLore(String term, String text) {
        addBulletedLore(term,text,null);
        return this;
    }
    public ItemBuilder addBulletedLore(String term, String text, ChatColor color) {
        lore.add(String.format("%s %s %s", ChatColor.getByChar(genesisTheme.getBullets()) + "*", ChatColor.getByChar(genesisTheme.getAccents()) + term, ChatColor.getByChar(genesisTheme.getSecondaryHeaderColor()) + text));
        abstractItem.getItemMeta().setLore(lore);
        return this;
    }
    public ItemBuilder addLore(List<String> text) { //adds the default can add compatability for custom color
        if (lore == null) {
            lore = new ArrayList<>();
        }
        for (String s : text) {
            addLore(s);
        }
        abstractItem.getItemMeta().setLore(lore);
        return this;
    }
    public ItemBuilder addLore(String text) {
        if (lore == null) {
            lore = new ArrayList<>();
        }
        lore.add(ChatColor.getByChar(genesisTheme.getBodyColor()) + text);
        abstractItem.getItemMeta().setLore(lore);
        return this;
    }

    public ItemBuilder setCustomModelData(int model) {
        abstractItem.getItemMeta().setCustomModelData(model);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        abstractItem.getItemMeta().setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder setUnstackable(boolean unstackable) {
        if (unstackable) {
            long l = Genesis.getMath().getRandom().nextLong();
            abstractItem.getItemMeta().getPersistentDataContainer().set(Genesis.getKeys()
                    .getMap()
                    .get("unstackable"), PersistentDataType.LONG, l);
        }
        return this;
    }

    public ItemStack build() {
        abstractItem.getItemStack().setItemMeta(abstractItem.getItemMeta());
        return abstractItem.getItemStack();
    }
    public String applyColor(ChatColor color, ChatColor themeColor, String text) {
        if (color == null) {
            return themeColor + text;
        }
        return color + text;
    }
}
