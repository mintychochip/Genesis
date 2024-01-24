package mintychochip.genesis.util;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON('7'),
    UNCOMMON('a'),
    RARE('9'),
    EPIC('5'),
    LEGENDARY('6'),
    ARTIFACT('c'),
    DEFAULT('f');

    public final char colorCode;

    Rarity(char colorCode) {
        this.colorCode = colorCode;
    }

    public char getColorCode() {
        return colorCode;
    }

    public ChatColor getChatColorCode() {
        return ChatColor.getByChar(colorCode);
    }

}
