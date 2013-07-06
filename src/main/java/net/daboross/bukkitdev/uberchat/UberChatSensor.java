/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat;

import net.md_5.bungee.api.ChatColor;

/**
 *
 * @author daboross
 */
public class UberChatSensor {

    public static String getSensoredMessage(String message) {
        String output = message;
        output = checkAndColors(output);
        output = trimMessage(output);
        output = replaceFullCaps(output);
        output = UberChatSwearChecker.swearCheck(output);
        return output;
    }

    private static String checkAndColors(String message) {
        return UberChatHelpers.translateColor(message);
    }

    private static String trimMessage(String message) {
        return message.trim();
    }

    private static String replaceFullCaps(String message) {
        String newMessage = ChatColor.stripColor(message);
        int totalChars = newMessage.length();
        int capChars = 0;
        int lowChars = 0;
        char[] charArray = newMessage.toCharArray();
        for (char c : charArray) {
            if (Character.isUpperCase(c)) {
                capChars++;
            } else if (Character.isLowerCase(c)) {
                lowChars++;
            }
        }
        if ((capChars > (lowChars * 2)) && totalChars > 5 || (capChars > 9)) {
            return UberChatHelpers.firstLetterCaps(message);
        } else {
            return message;
        }
    }
}
