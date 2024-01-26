package mintychochip.genesis.builder;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.color.GenesisColor;
import mintychochip.genesis.color.GenesisTheme;
import mintychochip.genesis.config.GenesisRegistry;
import mintychochip.genesis.container.AbstractItem;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    protected AbstractItem abstractItem;
    protected List<String> lore;
    protected GenesisTheme genesisTheme;

    public ItemBuilder(AbstractItem abstractItem, String genesisTheme) {
        this.abstractItem = abstractItem;
        this.genesisTheme = GenesisRegistry.getThemes().get(genesisTheme);
        abstractItem.getItemMeta().getPersistentDataContainer().set(Genesis.getKey("abstract"), PersistentDataType.BOOLEAN, true);
        if (abstractItem.isBoundOnCraft()) {
            abstractItem.getItemMeta().getPersistentDataContainer().set(Genesis.getKey("bind"), PersistentDataType.BOOLEAN, abstractItem.isBoundOnCraft());
        }
    }

    public ItemBuilder(AbstractItem abstractItem, GenesisTheme genesisTheme) {
        this.abstractItem = abstractItem;
        this.genesisTheme = genesisTheme;
    }

    public ItemBuilder setRawDisplayName(String name) {
        if(name == null) {
            return this;
        }
        abstractItem.getItemMeta().setDisplayName(name);
        return this;
    }

    public ItemBuilder setDisplayName(String name, char color) {
        if(name == null) {
            return this;
        }
        abstractItem.getItemMeta().setDisplayName(GenesisColor.applyColor(genesisTheme.getHeaderColor(), color, name));
        return this;
    }

    public ItemBuilder setDisplayName(String name, ChatColor chatColor) {
        if(name == null) {
            return this;
        }
        if (chatColor == null) {
            abstractItem.getItemMeta().setDisplayName(GenesisColor.applyColor(ChatColor.getByChar(genesisTheme.getHeaderColor()), null, name));
        } else {
            abstractItem.getItemMeta().setDisplayName(GenesisColor.applyColor(genesisTheme.getHeaderColor(), chatColor.getChar(), name));
        }
        return this;
    }

    public ItemBuilder addBulletedLore(String term, String text) {
        addBulletedLore(term, text, null);
        return this;
    }

    public ItemBuilder addBulletedLore(String term, String text, ChatColor color) {
        if(term == null || text == null) {
            return this;
        }
        lore.add(String.format("%s %s %s", ChatColor.getByChar(genesisTheme.getBullets()) + "*", ChatColor.getByChar(genesisTheme.getAccents()) + term, ChatColor.getByChar(genesisTheme.getSecondaryHeaderColor()) + text));
        abstractItem.getItemMeta().setLore(lore);
        return this;
    }

    public ItemBuilder addLore(List<String> text) { //adds the default can add compatability for custom color
        if(text == null) {
            return this;
        }
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
        if(text == null) {
            return this;
        }
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

    public ItemBuilder setAbstractItem(AbstractItem abstractItem) {
        this.abstractItem = abstractItem;
        return this;
    }

    public AbstractItem getAbstractItem() {
        return abstractItem;
    }
}
