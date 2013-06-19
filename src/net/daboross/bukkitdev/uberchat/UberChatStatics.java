/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat;

import org.bukkit.ChatColor;

/**
 *
 * @author daboross
 */
public class UberChatStatics {

    public static class FORMAT {

        /**
         * FORMAT for chat. First %s is the player's name, second %s is chat
         * message.
         */
        public static final String CHAT = ChatColor.BLACK + "#" + ChatColor.BLUE + "%s" + ChatColor.GRAY + " %s";
        /**
         * FORMAT for /msg. First %s is from player's name, second %s is to
         * player's name, third %s is message.s
         */
        public static final String MSG = ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + "%s" + ChatColor.DARK_BLUE + " -> " + ChatColor.BLUE + "%s" + ChatColor.DARK_BLUE + "]" + ChatColor.WHITE + " %s";
        /**
         * FORMAT for /msg shown to spies. First %s is from player's name,
         * second %s is to player's name, third %s is message.
         */
        public static final String MSG_SPY = ChatColor.RED + "[" + ChatColor.BLUE + "%s" + ChatColor.RED + " -> " + ChatColor.BLUE + "%s" + ChatColor.RED + "]" + ChatColor.WHITE + " %s";
        /**
         * FORMAT for announcer messages. First %s is the announcer's name.
         */
        public static final String ANNOUNCER = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "%s" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
        /**
         * FORMAT for /me. First %s is player's name, second %s is action.
         */
        public static final String ME = ChatColor.BLACK + " * " + ChatColor.BLUE + "%s" + ChatColor.GRAY + " %s";
    }

    public static class COLOR {

        /**
         * Main Color.
         */
        public static final String MAIN = ChatColor.AQUA.toString();
    }

    public static class PERMISSION {

        public static final String MSG_SPY = "uberchat.msgspy";
    }
}
