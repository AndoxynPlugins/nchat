package net.daboross.bukkitdev.uberchat;

import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author Dabo Ross
 */
public class UberChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerChatEvent(AsyncPlayerChatEvent apce) {
        format(apce);
        if (!checkForBack(apce)) {
            apce.setMessage(UberChatSensor.getSensoredMessage(apce.getMessage(), apce.getPlayer()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChatEventHigh(AsyncPlayerChatEvent apce) {
        UberChatHelpers.formatPlayerDisplayname(apce.getPlayer());
    }

    private void format(AsyncPlayerChatEvent evt) {
        evt.setFormat(UberChatStatics.FORMAT.CHAT);
    }

    private boolean checkForBack(AsyncPlayerChatEvent evt) {
        String msg = evt.getMessage().replaceAll("(?i)" + String.valueOf(ChatColor.COLOR_CHAR) + "[0-9A-FK-OR]", "").trim().toLowerCase(Locale.ENGLISH);
        if (msg.equals("back") || msg.equals("im back") || msg.equals("i'm back")) {
            String fullDisplay = evt.getPlayer().getDisplayName();
            String[] nameSplit = fullDisplay.split(" ");
            String name = nameSplit[nameSplit.length - 1];
            Bukkit.getServer().broadcastMessage(String.format(UberChatStatics.FORMAT.ANNOUNCER, ChatColor.BLUE + name + ChatColor.GRAY + " Is Back" + ChatColor.DARK_GRAY + "!"));
            evt.setCancelled(true);
            return true;
        } else {
            return false;
        }
    }
}
