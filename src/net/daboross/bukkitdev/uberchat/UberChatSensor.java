/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

/**
 *
 * @author daboross
 */
public class UberChatSensor {

    public static String getSensoredMessage(String message, Player p) {
        String output = message;
        output = checkAndColors(output);
        output = trimMessage(output);
        output = checkToggleMessage(output, p);
        output = replaceFullCaps(output, p);
        output = checkColorMessage(output, p);
        return output;
    }

    public static String checkAndColors(String message) {
        return UberChatHelpers.translateColor(message);
    }

    public static String trimMessage(String message) {
        return message.trim();
    }

    public static String checkToggleMessage(String message, Player p) {
        if (p.hasMetadata(UberChatStatics.TOGGLE_MESSAGE_ON_METADATA_KEY)) {
            List<MetadataValue> meta = p.getMetadata(UberChatStatics.TOGGLE_MESSAGE_ON_METADATA_KEY);
            if (meta.size() >= 1) {
                if (meta.get(0).asBoolean()) {
                    return UberChatHelpers.toggleCase(message);
                }
            }
        }
        return message;
    }

    public static String replaceFullCaps(String message, Player p) {
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
            p.sendMessage(UberChatStatics.CAPS_MESSAGE);
            return UberChatHelpers.firstLetterCaps(message);
        } else {
            return message;
        }
    }

    public static String checkColorMessage(String message, Player p) {
        if (p.hasMetadata(UberChatStatics.COLOR_MESSAGE_ON_METADATA_KEY)) {
            List<MetadataValue> meta = p.getMetadata(UberChatStatics.COLOR_MESSAGE_ON_METADATA_KEY);
            if (meta.size() >= 1 && meta.get(0).asBoolean()) {
                return Colorizor.getColorString(message);
            }
        }
        return message;
    }
}
