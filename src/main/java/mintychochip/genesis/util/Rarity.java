package mintychochip.genesis.util;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON('7'),
    UNCOMMON ('a'),
    RARE ('9'),
    EPIC ('5'),
    LEGENDARY ('6'),
    ARTIFACT ('c'),
    DEFAULT ('f');

    private final char color;
    Rarity(char color) {
        this.color = color;
    }
    public char getColor() {
        return color;
    }
    public ChatColor getLegacyColor() {
        return ChatColor.getByChar(color);
    }
}
