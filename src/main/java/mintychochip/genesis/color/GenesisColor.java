package mintychochip.genesis.color;

import org.bukkit.ChatColor;

public class GenesisColor {

    public static String applyColor(char theme, char color, String text) {
        return applyColor(ChatColor.getByChar(theme), ChatColor.getByChar(color), text);
    }

    public static String applyColor(ChatColor theme, ChatColor color, String text) {
        if (color != null) {
            return color + text;
        }
        return theme + text;
    }

    enum Colors {

    }
}
