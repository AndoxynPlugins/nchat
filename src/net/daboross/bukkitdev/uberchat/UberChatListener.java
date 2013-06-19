package net.daboross.bukkitdev.uberchat;

import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author Dabo Ross
 */
public class UberChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChatEvent(AsyncPlayerChatEvent apce) {
        if (apce.isCancelled()) {
            return;
        }
        format(apce);
        if (!checkForBack(apce)) {
            apce.setMessage(UberChatSensor.getSensoredMessage(apce.getMessage(), apce.getPlayer()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChatEventHigh(AsyncPlayerChatEvent apce) {
        Player p = apce.getPlayer();
        String newDisplayName = p.getDisplayName();
        if (newDisplayName.contains("_")) {
            newDisplayName = newDisplayName.replaceAll("_", " ");
        }
        String noColor = ChatColor.stripColor(newDisplayName);
        while (noColor.startsWith(" ")) {
            newDisplayName = newDisplayName.replaceFirst(" ", "");
            noColor = ChatColor.stripColor(newDisplayName);
        }
        if (noColor.length() > 16) {
            int lengthDiff = 15 + newDisplayName.length() - noColor.length();
            newDisplayName = newDisplayName.substring(0, lengthDiff);
            if (newDisplayName.endsWith(String.valueOf(ChatColor.COLOR_CHAR))) {
                newDisplayName = newDisplayName.substring(0, newDisplayName.length() - 1);
            }
            p.setDisplayName(newDisplayName);
        }
    }

    private void format(AsyncPlayerChatEvent evt) {
        evt.setFormat(UberChatStatics.CHAT_FORMAT);
    }

    private boolean checkForBack(AsyncPlayerChatEvent evt) {
        String msg = evt.getMessage().replaceAll("(?i)" + String.valueOf(ChatColor.COLOR_CHAR) + "[0-9A-FK-OR]", "").trim().toLowerCase(Locale.ENGLISH);
        if (msg.equals("back") || msg.equals("im back") || msg.equals("i'm back")) {
            String fullDisplay = evt.getPlayer().getDisplayName();
            String[] nameSplit = fullDisplay.split(" ");
            String name = nameSplit[nameSplit.length - 1];
            Bukkit.getServer().broadcastMessage(UberChatHelpers.formatName("UC") + " " + name + ChatColor.GRAY + " Is Back" + ChatColor.DARK_GRAY + "!");
            evt.setCancelled(true);
            return true;
        } else {
            return false;
        }
    }
}
