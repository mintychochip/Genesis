package mintychochip.genesis.config;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.color.GenesisTheme;
import mintychochip.genesis.config.abstraction.GenericConfig;
import mintychochip.genesis.config.abstraction.GenesisConfigurationSection;
import mintychochip.genesis.container.GenesisDropTableEntry;
import mintychochip.genesis.util.EnumUtil;
import mintychochip.genesis.util.GenesisConfigMarker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.*;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenesisConfig extends GenericConfig {

    private static final int numberOfThemes = 5;

    private Map<Character, RecipeChoice> choiceMap;

    public Set<String> ingredientsKeys;

    private final Set<NamespacedKey> customRecipes = new HashSet<>();
    public GenesisConfig(String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
        if (!loadThemes()) {
            //something
        }
        new BukkitRunnable() {
            public void run() {
                try {
                    loadRecipes();
                    smeltingRecipes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskLater(Genesis.getInstance(), 10L);
    }
    private NamespacedKey getCraftingResultKey(JavaPlugin plugin, String key) {
        if (Genesis.getItemManager().resultMappingContainsKey(key)) {
            return Genesis.getItemManager().getResultKeyFromString(key);
        }
        return new NamespacedKey(plugin, key);
    }


    private ItemStack getCraftingResult(String key) {
        if (Genesis.getItemManager().resultMappingContainsKey(key)) {
            return Genesis.getItemManager().getResultItemFromString(key);
        }
        return new ItemStack(Enum.valueOf(Material.class, key.toUpperCase()));
    }

    private boolean smeltingRecipes() throws IOException {
        GenesisConfigurationSection smeltingRecipes = getMainConfigurationSection(GenesisConfigMarker.smelting_recipes);
        for (String key : smeltingRecipes.getKeys(false)) {
            ItemStack result = getCraftingResult(key);
            NamespacedKey namespacedKey = getCraftingResultKey(Genesis.getInstance(), key);
            boolean a = Genesis.getItemManager().resultMappingContainsKey(key);
            boolean b = EnumUtil.isInEnum(Material.class, key.toUpperCase());
            if (!a && !b) {
                throw new IOException();
            }
            GenesisConfigurationSection recipeAtKey = smeltingRecipes.getConfigurationSection(key);
            if (recipeAtKey == null) {
                throw new IOException("Recipe cannot be null");
            }
            GenesisConfigurationSection ingredients = recipeAtKey.getConfigurationSection(GenesisConfigMarker.ingredients);
            String material = ingredients.getString(GenesisConfigMarker.smelting_material);
            boolean c = Genesis.getItemManager().itemMappingContainsKey(material);
            boolean d = EnumUtil.isInEnum(Material.class, material.toUpperCase());
            float cookXP = (float) recipeAtKey.getDouble(GenesisConfigMarker.cookingXp);
            int cookTime = recipeAtKey.getInt(GenesisConfigMarker.cookingTime);

            if (c) {
                ItemStack ingredient = Genesis.getItemManager().getRecipeItemFromString(material);
                Bukkit.addRecipe(new FurnaceRecipe(namespacedKey, result, new ExactChoice(ingredient), cookXP, cookTime));
            }
            if (d) {
                Bukkit.addRecipe(new FurnaceRecipe(namespacedKey, result, new RecipeChoice.MaterialChoice(Enum.valueOf(Material.class, material.toUpperCase())), cookXP, cookTime));
            }
        }
        return true;
    }

    private boolean loadRecipes() throws IOException { //da mega method //have to make it case insensitive
        ConfigurationSection recipes = configReader.getConfigurationSection(GenesisConfigMarker.crafting_recipes);
        for (String key : recipes.getKeys(false)) {
            ItemStack result = null;
            NamespacedKey namespacedKey = null;
            boolean a = Genesis.getItemManager().resultMappingContainsKey(key);
            boolean b = EnumUtil.isInEnum(Material.class, key.toUpperCase());
            if (a) {
                result = Genesis.getItemManager().getResultItemFromString(key);
                namespacedKey = Genesis.getItemManager().getResultKeyFromString(key);
            }
            if (b) {
                result = new ItemStack(Enum.valueOf(Material.class, key.toUpperCase()));
                namespacedKey = new NamespacedKey(Genesis.getInstance(), key);
            }
            if (!a && !b) {
                throw new IOException();
            }
            ConfigurationSection recipeAtKey = recipes.getConfigurationSection(key);
            if (recipeAtKey == null) {
                throw new IOException("Recipe is null");
            }
            ConfigurationSection ingredients = recipeAtKey.getConfigurationSection("ingredients");
            if (ingredients == null) {
                throw new IOException("Ingredients cannot be null");
            }
            CraftingRecipe recipe;
            if (recipeAtKey.getBoolean(GenesisConfigMarker.shaped_recipe)) {
                ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, result);
                shapedRecipe.shape(recipeAtKey.getStringList("shape").toArray(new String[0]));
                for (String ingredientsKey : ingredients.getKeys(false)) {
                    ingredientsKeys = ingredients.getKeys(false);
                    boolean c = Genesis.getItemManager().itemMappingContainsKey(ingredientsKey);
                    boolean d = EnumUtil.isInEnum(Material.class, ingredientsKey.toUpperCase());
                    String shapeCode = ingredients.getString(ingredientsKey);
                    if (shapeCode == null) {
                        throw new IOException("cannot be null");
                    }
                    if (shapeCode.length() != 1) {
                        throw new IOException("cannot be other than 1");
                    }
                    if (c) {
                        ItemStack ingredient = Genesis.getItemManager().getRecipeItemFromString(ingredientsKey);
                        shapedRecipe.setIngredient(shapeCode.charAt(0), new RecipeChoice.ExactChoice(ingredient));
                    }
                    if (d) {
                        shapedRecipe.setIngredient(shapeCode.charAt(0), Enum.valueOf(Material.class, ingredientsKey.toUpperCase()));
                    }
                }
                recipe = shapedRecipe;
                choiceMap = shapedRecipe.getChoiceMap();
            } else {
                ShapelessRecipe shapelessRecipe = shapelessRecipe(namespacedKey, result);
                for (String ingredientsKey : ingredients.getKeys(false)) {
                    ItemStack ingredient = null;
                    int count = ingredients.getInt(ingredientsKey);
                    boolean c = Genesis.getItemManager().itemMappingContainsKey(ingredientsKey);
                    boolean d = EnumUtil.isInEnum(Material.class, ingredientsKey.toUpperCase());
                    if (c) {
                        ingredient = Genesis.getItemManager().getRecipeItemFromString(ingredientsKey);
                        for (int i = 0; i < count; i++) {
                            shapelessRecipe.addIngredient(new ExactChoice(ingredient));
                        }
                    }
                    if (d) {
                        shapelessRecipe.addIngredient(count, Enum.valueOf(Material.class, ingredientsKey.toUpperCase()));
                    }
                }
                recipe = shapelessRecipe;
            }
            customRecipes.add(recipe.getKey());
            Bukkit.addRecipe(recipe);
        }
        return true;
    }

    public Map<Character, RecipeChoice> getChoiceMap() {
        return choiceMap;
    }

    private ShapelessRecipe shapelessRecipe(NamespacedKey key, ItemStack itemStack) {
        return new ShapelessRecipe(key, itemStack);
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
