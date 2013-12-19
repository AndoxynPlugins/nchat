/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bungeedev.nchat;

import net.md_5.bungee.api.ChatColor;

public class ChatSensor {

    public static String formatPlayerDisplayname(String name) {
        name = ChatColor.GRAY + getSensoredMessage(name);
        String noColor = ChatColor.stripColor(name).trim();
        while (noColor.startsWith(" ")) {
            name = name.replaceFirst(" ", "");
            noColor = ChatColor.stripColor(name);
        }
        if (name.length() > 16) {
            name = name.substring(0, 16);
            if (name.endsWith(String.valueOf(ChatColor.COLOR_CHAR))) {
                name = name.substring(0, 15);
            }
        }
        return name;
    }

    public static String getSensoredMessage(String message) {
        String output = message;
        output = checkAndColors(output);
        output = trimMessage(output);
        output = replaceFullCaps(output);
        output = SwearChecker.swearCheck(output);
        return output;
    }

    private static String checkAndColors(String message) {
        return StringUtils.translateColor(message);
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
            return StringUtils.firstLetterCaps(message);
        } else {
            return message;
        }
    }
}
