/*
 * Copyright (C) 2013-2014 Dabo Ross <http://www.daboross.net/>
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

import static net.md_5.bungee.api.ChatColor.*;

public class Statics {

    public static class Format {

        public static final String CHAT = DARK_GRAY + "#" + BLUE + "%s" + GRAY + " %s";
        public static final String MSG = DARK_BLUE + "[" + BLUE + "%s" + DARK_BLUE + " -> " + BLUE + "%s" + DARK_BLUE + "]" + WHITE + " %s";
        public static final String MSG_SPY = DARK_RED + "[" + BLUE + "%s" + DARK_RED + " -> " + BLUE + "%s" + DARK_RED + "]" + AQUA + " %s";
        public static final String ME = DARK_GRAY + "*" + BLUE + "%s" + GRAY + " %s";
        public static final String SHOUT = DARK_GRAY + "[" + BLUE + "%s" + DARK_GRAY + "]" + GREEN + " %s";
        public static final String STAFFCHAT = AQUA + "%%" + BLUE + "%s" + AQUA + " %s";
    }

    public static class Strings {

        public static final String MSG_YOU_REPRESENTATION = GOLD + "You";
    }

    public static class Permission {

        public static final String MSG_SPY = "nchat.msgspy";
        public static final String STAFF_CHAT = "nchat.staffchat";
    }
}
