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

    /**
     * Format for chat. First %s is the player's name, second %s is chat
     * message.
     */
    public static final String CHAT_FORMAT = ChatColor.BLACK + "#" + ChatColor.BLUE + "%s" + ChatColor.GRAY + " %s";
    /**
     * Format for /me. First %s is player's name, second %s is action.
     */
    public static final String ME_FORMAT = UberChatHelpers.formatName("UC") + ChatColor.BLUE + " %s" + ChatColor.GRAY + " %s";
    /**
     * Format for /msg. First %s is from player's name, second %s is to player's
     * name, third %s is message.s
     */
    public static final String MSG_FORMAT = ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + "%s" + ChatColor.DARK_BLUE + " -> " + ChatColor.BLUE + "%s" + ChatColor.DARK_BLUE + "]" + ChatColor.AQUA + " %s";
    /**
     * Format for /msg. First %s is from player's name, second %s is to player's
     * name, third %s is message.s
     */
    public static final String MSG_SPY_FORMAT = ChatColor.RED + "[" + ChatColor.BLUE + "%s" + ChatColor.RED + " -> " + ChatColor.BLUE + "%s" + ChatColor.RED + "]" + ChatColor.AQUA + " %s";

    public static class Permission {
        public static final String MSG_SPY = "uberchat.msgspy";
    }
}
