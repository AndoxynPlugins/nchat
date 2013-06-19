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

    public static final String CAPS_MESSAGE = ChatColor.RED + ("I'm sorry, but your chat message contains to many uppercase letters.");
    public static final String CHAT_FORMAT = ChatColor.BLACK + "#" + ChatColor.BLUE + "%s" + ChatColor.GRAY + " %s";
    public static final String TOGGLE_MESSAGE_ON_METADATA_KEY = "net.daboross.uberchat.togglemessageon";
    public static final String COLOR_MESSAGE_ON_METADATA_KEY = "net.daboross.uberchat.colormessageon";
    public static final String ME_FORMAT = ChatColor.DARK_PURPLE + " * " + ChatColor.BLUE + "%s" + ChatColor.GRAY + " %s";
}
