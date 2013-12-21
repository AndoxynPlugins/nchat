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

public class Statics {

    public static class Format {

        public static final String CHAT = ChatColor.DARK_GRAY + "#" + ChatColor.BLUE + "%s" + ChatColor.GRAY + " %s";
        public static final String MSG = ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + "%s" + ChatColor.DARK_BLUE + " -> " + ChatColor.BLUE + "%s" + ChatColor.DARK_BLUE + "]" + ChatColor.WHITE + " %s";
        public static final String MSG_SPY = ChatColor.DARK_RED + "[" + ChatColor.BLUE + "%s" + ChatColor.DARK_RED + " -> " + ChatColor.BLUE + "%s" + ChatColor.DARK_RED + "]" + ChatColor.AQUA + " %s";
        public static final String ME = ChatColor.DARK_GRAY + "*" + ChatColor.BLUE + "%s" + ChatColor.GRAY + " %s";
        public static final String SHOUT = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "!" + ChatColor.BLUE + "%s" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " %s";
        public static final String STAFFCHAT = ChatColor.AQUA + "%%" + ChatColor.BLUE + "%s" + ChatColor.AQUA + " %s";
    }

    public static class Strings {

        public static final String MSG_YOU_REPRESENTATION = ChatColor.GOLD + "You";
    }

    public static class Permission {

        public static final String MSG_SPY = "nchat.msgspy";
        public static final String STAFF_CHAT = "nchat.staffchat";
    }
}
