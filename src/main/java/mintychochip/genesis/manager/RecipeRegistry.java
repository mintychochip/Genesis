package mintychochip.genesis.manager;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RecipeRegistry {
    private Map<NamespacedKey, ItemStack> recipeItems = new HashMap<>(); //item to be used in config,

    private Map<NamespacedKey,ItemStack> recipeResults = new HashMap<>();

    private Map<String,NamespacedKey> resultMappings = new HashMap<>();

    private Map<String,NamespacedKey> itemMappings = new HashMap<>();

    public void addRecipeItem(JavaPlugin instance, String displayName, ItemStack item) {
        this.addRecipeItem(new NamespacedKey(instance,displayName),item);
    }
    public void addRecipeItem(NamespacedKey key, ItemStack item) {
        recipeItems.put(key,item);
    }
    public ItemStack getRecipeItem(NamespacedKey key) {
        return recipeItems.get(key);
    }
    public ItemStack getRecipeItem(JavaPlugin instance, String displayName) {
        return recipeItems.get(new NamespacedKey(instance,displayName));
    }
    public void addRecipeItems(JavaPlugin instance, Map<String,ItemStack> items) throws IOException {
        addToMap(itemMappings,recipeItems,instance,items);
    }
    public void addRecipeResults(JavaPlugin instance, Map<String,ItemStack> items) throws IOException {
        addToMap(resultMappings,recipeResults,instance,items);
    }
    private void addToMap(Map<String,NamespacedKey> mappings, Map<NamespacedKey,ItemStack> values,JavaPlugin instance,Map<String,ItemStack> items) throws IOException {
        for (String item : items.keySet()) {
            ItemStack itemStack = items.get(item);
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(itemMeta == null) {
                throw new IOException("item meta cannot be null!");
            }
            NamespacedKey obfuscatedKey = new NamespacedKey(instance,convertStringToKeyable(itemMeta.getDisplayName()));
            mappings.put(item,obfuscatedKey);
            values.put(obfuscatedKey,itemStack);
        }
    }
    public String convertStringToKeyable(String text) {
        return ChatColor.stripColor(text.strip().replace(" ", "_").toLowerCase().replace("'",""));
    }
    public ItemStack getRecipeItemFromString(String item) {
        return recipeItems.get(itemMappings.get(item));
    }
    public ItemStack getResultItemFromString(String item) {
        return recipeResults.get(resultMappings.get(item));
    }
    public NamespacedKey getResultKeyFromString(String item) {
        return resultMappings.get(item);
    }
    public NamespacedKey getItemKeyFromString(String item) {
        return itemMappings.get(item);
    }
    public Map<NamespacedKey, ItemStack> getRecipeItems() {
        return recipeItems;
    }

    public Map<String, NamespacedKey> getItemMappings() {
        return itemMappings;
    }

    public Map<NamespacedKey, ItemStack> getRecipeResults() {
        return recipeResults;
    }

    public Map<String, NamespacedKey> getResultMappings() {
        return resultMappings;
    }
    public boolean resultMappingContainsKey(String key) {
        return resultMappings.containsKey(key);
    }
    public boolean itemMappingContainsKey(String key) {
        return itemMappings.containsKey(key);
    }

}
