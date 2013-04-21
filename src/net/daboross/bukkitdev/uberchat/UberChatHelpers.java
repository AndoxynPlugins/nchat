package net.daboross.bukkitdev.uberchat;

import org.bukkit.ChatColor;

/**
 *
 * @author daboross
 */
public class UberChatHelpers {

    public static String formatName(String name) {
        return ChatColor.DARK_GRAY + "[" + ChatColor.RED + name + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY;
    }

    public static String toggleCase(String input) {
        StringBuilder outputBuilder = new StringBuilder(input.length());
        String[] words = input.split(" ");
        boolean first = true;
        for (String word : words) {
            if (first) {
                first = false;
            } else {
                outputBuilder.append(" ");
            }
            if (word.length() == 0) {
                continue;
            }
            outputBuilder.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1, word.length()).toLowerCase());
        }
        return outputBuilder.toString();
//        String output = "";
//        char[] in = input.toCharArray();
//        int state = 1;//0 for in word, 1 for new word.
//        for (int i = 0; i < in.length; i++) {
//            char c = in[i];
//            if (c == ' ') {
//                state = 1;
//                output += c;
//            } else if (Character.isUpperCase(c) || Character.isLowerCase(c)) {
//                if (state == 1) {
//                    output += Character.toUpperCase(c);
//                    state = 0;
//                } else {
//                    if (i + 1 < in.length && in[i + 1] == ' ') {
//                        output += c;
//                    } else {
//                        output += Character.toLowerCase(c);
//                    }
//                }
//            } else if (c == ChatColor.COLOR_CHAR) {
//                output += c;
//            } else {
//                state = 1;
//                output += c;
//            }
//        }
//        return output;
    }
}
