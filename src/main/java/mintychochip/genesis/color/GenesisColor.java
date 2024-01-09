package mintychochip.genesis.color;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.ChatColor;

public class GenesisColor {

    enum Colors {

    }

    public static String applyColor(char theme, char color, String text) {
        return applyColor(ChatColor.getByChar(theme), ChatColor.getByChar(color), text);
    }

    public static String applyColor(ChatColor theme, ChatColor color, String text) {
        if (color != null) {
            return color + text;
        }
        return theme + text;
    }
}
