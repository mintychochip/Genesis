package mintychochip.genesis.container.items;

import com.google.gson.Gson;
import mintychochip.genesis.Genesis;
import mintychochip.genesis.color.GenesisTheme;
import mintychochip.genesis.config.GenesisRegistry;
import mintychochip.genesis.config.abstraction.GenesisConfigurationSection;
import mintychochip.genesis.container.items.actions.ActionPacket;
import mintychochip.genesis.container.items.interfaces.Appraisable;
import mintychochip.genesis.container.items.interfaces.Embeddable;
import mintychochip.genesis.events.ActionEventType;
import mintychochip.genesis.util.GenesisConfigMarker;
import mintychochip.genesis.util.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextColor.color;

public class AbstractItem {
    protected final JavaPlugin instance;
    private final ItemStack itemStack;
    private final boolean boundOnCraft;
    private final ItemMeta itemMeta;

    public static class ItemBuilder {
        protected final JavaPlugin instance;
        protected final ItemStack itemStack;
        protected ItemMeta itemMeta;
        protected final boolean boundOnCraft;
        protected GenesisTheme genesisTheme;
        protected List<String> lore;

        public ItemBuilder(JavaPlugin instance, ItemStack itemStack, boolean boundOnCraft) {
            this.instance = instance;
            this.itemMeta = itemStack.getItemMeta();
            this.itemStack = itemStack;
            this.boundOnCraft = boundOnCraft;
        }

        public ItemBuilder(JavaPlugin instance, Material material, boolean boundOnCraft, String genesisTheme) {
            this.instance = instance;
            this.itemStack = new ItemStack(material);
            this.itemMeta = itemStack.getItemMeta();
            if (itemMeta != null) {
                lore = new ArrayList<>();
            }
            this.boundOnCraft = boundOnCraft;
            this.genesisTheme = GenesisRegistry.getThemes().get(genesisTheme);
        }

        public ItemBuilder setUnbreakable(boolean unbreakable) {
            itemMeta.setUnbreakable(unbreakable);
            return this;
        }


        public ItemBuilder setDisplayName(String name) {
            if (name == null) {
                return this;
            }
            itemMeta.setDisplayName(name);
            return this;
        }

        public ItemBuilder setUnstackable(boolean unstackable) {
            if (unstackable) {
                long l = Genesis.getMath().getRandom().nextLong();
                itemMeta.getPersistentDataContainer().set(Genesis.getKeys()
                        .getMap()
                        .get("unstackable"), PersistentDataType.LONG, l);
            }
            return this;
        }

        public ItemBuilder addLore(String text) {
            if (text == null) {
                return this;
            }
            if (lore == null) {
                lore = new ArrayList<>();
            }
            lore.add(ChatColor.getByChar(genesisTheme.getBodyColor()) + text);
            itemMeta.setLore(lore);
            return this;
        }

        public ItemBuilder addLore(List<String> text) { //adds the default can add compatability for custom color
            if (text == null) {
                return this;
            }
            if (lore == null) {
                lore = new ArrayList<>();
            }
            for (String s : text) {
                addLore(s);
            }
            itemMeta.setLore(lore);
            return this;
        }

        public ItemBuilder setCustomModelData(int model) {
            itemMeta.setCustomModelData(model);
            return this;
        }

        public AbstractItem build() {
            if (itemStack.setItemMeta(itemMeta)) {
                return new AbstractItem(this);
            }
            return null;
        }
    }

    public static class ConfigurationItemBuilder extends ItemBuilder {
        protected GenesisConfigurationSection main;

        public ConfigurationItemBuilder(JavaPlugin instance, Material material, GenesisConfigurationSection main, boolean boundOnCraft, String genesisTheme) {
            super(instance, material, boundOnCraft, genesisTheme);
            this.main = main;
        }

        public ConfigurationItemBuilder(JavaPlugin instance, GenesisConfigurationSection main, boolean boundOnCraft, String genesisTheme) {
            super(instance, main.enumFromSection(Material.class, GenesisConfigMarker.material), boundOnCraft, genesisTheme);
            this.main = main;
        }

        public void defaultSettings() {
            this.setUnbreakable(main.getBoolean(GenesisConfigMarker.unbreakable))
                    .setCustomModelData(main.getInt(GenesisConfigMarker.custom_model))
                    .setDisplayName(main.getString(GenesisConfigMarker.display_name))
                    .setUnstackable(main.getBoolean(GenesisConfigMarker.unstackable));
            List<String> lore = main.getStringList(GenesisConfigMarker.lore);
            if (lore != null) {
                this.addLore(lore);
            }
        }

        public AbstractItem defaultBuild() {
            defaultSettings();
            return super.build();
        }
    }

    public static class EmbeddedDataBuilder extends ConfigurationItemBuilder {
        protected Map<NamespacedKey, Embeddable> embeddableMap = new HashMap<>();
        protected Embeddable itemEmbeddable;

        public EmbeddedDataBuilder(JavaPlugin instance, Material material, GenesisConfigurationSection main, boolean boundOnCraft, String genesisTheme, Embeddable embeddable) {
            super(instance, material, main, boundOnCraft, genesisTheme);
            itemEmbeddable = embeddable;
            embeddableMap.put(embeddable.getKey(), embeddable);
        }

        public EmbeddedDataBuilder(JavaPlugin instance, GenesisConfigurationSection main, boolean boundOnCraft, String genesisTheme, Embeddable embeddable) {
            super(instance, main, boundOnCraft, genesisTheme);
            itemEmbeddable = embeddable;
            embeddableMap.put(embeddable.getKey(), embeddable);
        }

        public EmbeddedDataBuilder setRarity(Rarity rarity) {
            if (itemEmbeddable instanceof Appraisable apr) {
                apr.setRarity(rarity);
            }
            return this;
        }

        public EmbeddedDataBuilder addClickEvent(String actionPacketKey, ActionPacket packet) {
            return this.addItemActivationEvent(actionPacketKey,packet,ActionEventType.CLICK);
        }
        public EmbeddedDataBuilder addItemActivationEvent(String actionPacketKey, ActionPacket packet, ActionEventType type) {
            Embeddable embeddable = null;
            if(!embeddableMap.containsKey(type.getKey())) {
                embeddable = new ActionData(type);
                embeddableMap.put(embeddable.getKey(),embeddable);
            } else {
                embeddable = embeddableMap.get(type.getKey());
            }
            if(embeddable instanceof ActionData acd) {
                acd.addActionPacket(actionPacketKey,packet);
            }
            return this;
        }

        public EmbeddedDataBuilder addConsumeEvent(String actionPacketKey, ActionPacket actionPacket) {
            return this.addItemActivationEvent(actionPacketKey,actionPacket,ActionEventType.CONSUME);
        }
        public AbstractItem defaultBuild() {
            defaultSettings();
            Rarity rarity = main.enumFromSection(Rarity.class, GenesisConfigMarker.rarity);
            if (rarity != null) {
                this.setRarity(rarity).setDisplayName(rarity.getLegacyColor() + itemMeta.getDisplayName());
            }
            Gson gson = new Gson();
            for (Embeddable embeddable : embeddableMap.values()) {
                if (embeddable.getKey() == null) {
                    throw new RuntimeException("Embeddable key was null: " + embeddable.getType());
                }
                itemMeta.getPersistentDataContainer().set(embeddable.getKey(), PersistentDataType.STRING, gson.toJson(embeddable));
            }
            return super.build();
        }
    }

    protected AbstractItem(ItemBuilder itemBuilder) {
        this.instance = itemBuilder.instance;
        this.itemStack = itemBuilder.itemStack;
        this.itemMeta = itemBuilder.itemMeta;
        this.boundOnCraft = itemBuilder.boundOnCraft;
    }

    public boolean addClickListener(Action action) {
        return false;
    }

    public JavaPlugin getInstance() {
        return instance;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public boolean isBoundOnCraft() {
        return boundOnCraft;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }
}
