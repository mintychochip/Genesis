package mintychochip.genesis.config;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.color.GenesisTheme;
import mintychochip.genesis.util.EnumUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenesisConfig extends GenericConfig {

    private static int numberOfThemes = 5;

    private Set<NamespacedKey> customRecipes = new HashSet<>();

    public GenesisConfig(String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
        if (!loadThemes()) {
            //something
        }
        new BukkitRunnable() {
            public void run() {
                try {
                    loadRecipes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskLater(Genesis.getInstance(),10L);
    }
    private boolean loadRecipes() throws IOException {
        ConfigurationSection recipes = configReader.getConfigurationSection("recipes");
        for (String key : recipes.getKeys(false)) {
            ItemStack result = null;
            NamespacedKey namespacedKey = null;
            boolean a = Genesis.getItemManager().resultMappingContainsKey(key);
            boolean b = EnumUtil.isInEnum(Material.class,key);
            if (a) {
                result = Genesis.getItemManager().getResultItemFromString(key);
                namespacedKey = Genesis.getItemManager().getResultKeyFromString(key);
            }
            if(b) {
                result = new ItemStack(Enum.valueOf(Material.class,key.toUpperCase()));
                namespacedKey = new NamespacedKey(Genesis.getInstance(),key);
            }
            if (!a && !b) {
                throw new IOException();
            }
            ConfigurationSection recipeAtKey = recipes.getConfigurationSection(key);
            if(recipeAtKey == null) {
                throw new IOException("Recipe is null");
            }
            ConfigurationSection ingredients = recipeAtKey.getConfigurationSection("ingredients");
            if(ingredients == null) {
                throw new IOException("Ingredients cannot be null");
            }
            CraftingRecipe recipe = null;
            if(recipeAtKey.getBoolean("shaped")) {
                ShapedRecipe shapedRecipe = shapedRecipe(namespacedKey, result, recipeAtKey.getStringList("shape").toArray(new String[0]));
            } else {
                ShapelessRecipe shapelessRecipe = shapelessRecipe(namespacedKey, result);
                for (String ingredientsKey : ingredients.getKeys(false)) {
                    ItemStack ingredient = null;
                    int count = ingredients.getInt(ingredientsKey);
                    boolean c = Genesis.getItemManager().itemMappingContainsKey(ingredientsKey);
                    boolean d = EnumUtil.isInEnum(Material.class, ingredientsKey.toUpperCase());
                    if(c) {
                        ingredient = Genesis.getItemManager().getRecipeItemFromString(ingredientsKey);
                        for(int i = 0; i < count; i++) {
                            shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(ingredient));
                        }
                    }
                    if(d) {
                        shapelessRecipe.addIngredient(count,Enum.valueOf(Material.class,ingredientsKey.toUpperCase()));
                    }
                }
                recipe = shapelessRecipe;
            }
            customRecipes.add(recipe.getKey());
            Bukkit.addRecipe(recipe);
        }
        return true;
    }
    private ShapelessRecipe shapelessRecipe(NamespacedKey key, ItemStack itemStack) {
        return new ShapelessRecipe(key,itemStack);
    }
    private ShapedRecipe shapedRecipe(NamespacedKey key, ItemStack itemStack, String[] shape) {
        ShapedRecipe shapedRecipe = new ShapedRecipe(key, itemStack);
        shapedRecipe.shape(shape);
        return shapedRecipe;
    }

    private boolean loadThemes() {
        ConfigurationSection themes = configReader.getConfigurationSection("themes");
        for (String key : themes.getKeys(false)) {
            String string = themes.getString(key);
            if (string == null) {
                Genesis.getInstance().getLogger().warning("Failed to load color themes");
                return false;
            }
            char[] charArray = string.toCharArray();
            if (charArray.length > numberOfThemes) {
                return false;
            }
            GenesisRegistry.getThemes().put(key.toUpperCase(), new GenesisTheme(charArray));
        }
        return true;
    }

    public Set<NamespacedKey> getCustomRecipes() {
        return customRecipes;
    }
}
