package net.daboross.bukkitdev.uberchat;

import java.util.Random;
import org.bukkit.ChatColor;

/**
 *
 * @author Dabo Ross <Dabo.Ross at daboross.net>
 */
public class Colorizor {

    private static Random r = new Random();
    private static int num = 0;

    public static String colorize(String orig) {
        char[] list = ChatColor.stripColor(orig).toCharArray();
        if (list.length == 0) {
            return "";
        }
        for (int i = 0; i < list.length; i++) {
        }
        String colorizedString = "";
        for (int i = 0; i < list.length; i++, num++) {
            if (list[i] != ' ') {
                if (num > 8) {
                    num = 0;
                }
                colorizedString += getColor(num);
            }
            colorizedString += list[i];
        }
        return colorizedString;
    }
    private static final String[] colorList = new String[]{ChatColor.AQUA.toString(), ChatColor.BLUE.toString(), ChatColor.DARK_BLUE.toString(), ChatColor.DARK_PURPLE.toString(), ChatColor.DARK_RED.toString(), ChatColor.RED.toString(), ChatColor.GOLD.toString(), ChatColor.GREEN.toString(), ChatColor.DARK_GREEN.toString()};

    private static String getColor(int i) {
        int v = i;
        while (v > 8) {
            v -= 8;
        }
        return colorList[v];
    }
    private static final String[] allColors = new String[]{ChatColor.AQUA.toString(), ChatColor.BLACK.toString(), ChatColor.BLUE.toString(), ChatColor.DARK_AQUA.toString(), ChatColor.DARK_BLUE.toString(), ChatColor.DARK_GRAY.toString(), ChatColor.DARK_GREEN.toString(), ChatColor.DARK_PURPLE.toString(), ChatColor.DARK_RED.toString(), ChatColor.GOLD.toString(), ChatColor.GRAY.toString(), ChatColor.GREEN.toString(), ChatColor.LIGHT_PURPLE.toString(), ChatColor.RED.toString(), ChatColor.WHITE.toString(), ChatColor.YELLOW.toString()};

    public static String randomColor() {
        return allColors[r.nextInt(allColors.length)];
    }
}
