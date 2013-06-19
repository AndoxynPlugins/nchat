package net.daboross.bukkitdev.uberchat;

import org.bukkit.ChatColor;

/**
 *
 * @author Dabo Ross <Dabo.Ross at daboross.net>
 */
public class Colorizor {

    private static int num = 0;

    public static String getColorString(String input) {
        char[] list = ChatColor.stripColor(input).toCharArray();
        if (list.length == 0) {
            return "";
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
    private static final String[] colorList = {ChatColor.AQUA.toString(), ChatColor.BLUE.toString(), ChatColor.DARK_BLUE.toString(), ChatColor.DARK_PURPLE.toString(), ChatColor.DARK_RED.toString(), ChatColor.RED.toString(), ChatColor.GOLD.toString(), ChatColor.GREEN.toString(), ChatColor.DARK_GREEN.toString()};

    private static String getColor(int i) {
        int v = i;
        while (v > 8) {
            v -= 8;
        }
        return colorList[v];
    }
}
