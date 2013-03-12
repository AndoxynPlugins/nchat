package net.daboross.bukkitdev.uberchat;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.metadata.MetadataValue;

/**
 *
 * @author Dabo Ross
 */
public class UberChatListener implements Listener {

    private static final String capsMessage = ChatColor.RED + Colorizor.colorize("I'm sorry, but your chat message contains to many uppercase letters.");

    public UberChatListener() {
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChatEvent(AsyncPlayerChatEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        if (!whatCheck(evt)) {
            swearCheck(evt);
            toggleCheck(evt);
            capsCheck(evt);
            colorCheck(evt);
            nameCheck(evt);
        }
    }

    private void colorCheck(AsyncPlayerChatEvent evt) {
        Player p = evt.getPlayer();
        if (p.hasMetadata("isMessageColorOn")) {
            List<MetadataValue> meta = p.getMetadata("isMessageColorOn");
            if (meta.size() >= 1) {
                if (meta.get(0).asBoolean()) {
                    evt.setMessage(Colorizor.colorize(evt.getMessage()));
                }
            }
        }
    }

    private void capsCheck(AsyncPlayerChatEvent evt) {
        Player p = evt.getPlayer();
        if (p.hasPermission("uberchat.ignorecaps")) {
            return;
        }
        String message = ChatColor.stripColor(evt.getMessage());
        int totalChars = message.length();
        int capChars = 0;
        int lowChars = 0;
        char[] charArray = message.toCharArray();
        for (char c : charArray) {
            if (Character.isUpperCase(c)) {
                capChars++;
            } else if (Character.isLowerCase(c)) {
                lowChars++;
            }
        }
        if ((capChars > (lowChars * 2)) && totalChars > 5 || (capChars > 9)) {
            p.sendMessage(capsMessage);
            evt.setMessage(UberChatHelpers.toggleCase(evt.getMessage()));
        }
    }

    private void nameCheck(AsyncPlayerChatEvent evt) {
        String name = ChatColor.stripColor(evt.getPlayer().getDisplayName());
        if (name.length() > 22) {
            evt.getPlayer().sendMessage(Colorizor.colorize(UberChatHelpers.toggleCase("Your Name Is Very Long! use /nick to shorten it!")));
            evt.getPlayer().sendMessage(Colorizor.colorize(UberChatHelpers.toggleCase("Your Name Is Very Long! use /nick to shorten it!")));
            evt.getPlayer().sendMessage(Colorizor.colorize(UberChatHelpers.toggleCase("Your Name Is Very Long! use /nick to shorten it!")));
        }
    }

    private void toggleCheck(AsyncPlayerChatEvent evt) {
        Player p = evt.getPlayer();
        if (p.hasMetadata("isMessageToggleOn")) {
            List<MetadataValue> meta = p.getMetadata("isMessageToggleOn");
            if (meta.size() >= 1) {
                if (meta.get(0).asBoolean()) {
                    evt.setMessage(UberChatHelpers.toggleCase(evt.getMessage()));
                }
            }
        }
    }

    private boolean whatCheck(AsyncPlayerChatEvent evt) {
        String msg = ChatColor.stripColor(evt.getMessage()).toLowerCase();
        if (msg.equals("back") || msg.equals("im back") || msg.equals("i'm back")) {
            String fullDisplay = evt.getPlayer().getDisplayName();
            String[] nameSplit = fullDisplay.split(" ");
            String name = nameSplit[nameSplit.length - 1];
            Bukkit.getServer().broadcastMessage(UberChatHelpers.formatName("Announcer") + " " + name + ChatColor.GRAY + " Is Back" + ChatColor.DARK_GRAY + "!");
            evt.setCancelled(true);
            return true;
        } else {
            return false;
        }
    }

    private void swearCheck(AsyncPlayerChatEvent evt) {
        String colorMsg = evt.getMessage();
        if (colorMsg.contains("fuck")) {
            evt.setMessage(colorMsg.replaceAll("fuck", "barnacles"));
        }
        String nonColorMsg = ChatColor.stripColor(colorMsg);
        if (nonColorMsg.contains("fuck")) {
            evt.setMessage(nonColorMsg.replaceAll("fuck", "barnacles"));
        }
    }
}
