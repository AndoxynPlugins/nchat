package net.daboross.bukkitdev.uberchat;

import java.util.List;
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

    private static final String capsMessage = ChatColor.RED + "I'm sorry, but your chat message contains to many uppercase letters.";

    public UberChatListener() {
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChatEvent(AsyncPlayerChatEvent evt) {
        if (evt.isCancelled()) {
            return;
        }
        toggleCheck(evt);
        checkCaps(evt);
        checkColor(evt);
        checkName(evt);
    }

    private void checkColor(AsyncPlayerChatEvent evt) {
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

    private void checkCaps(AsyncPlayerChatEvent evt) {
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
            evt.setMessage(toggleCase(evt.getMessage()));
        }
    }

    private String toggleCase(String input) {
        String output = "";
        char[] in = input.toCharArray();
        int state = 1;//0 for in word, 1 for new word.
        for (int i = 0; i < in.length; i++) {
            char c = in[i];
            if (c == ' ') {
                state = 1;
                output += c;
            } else if (Character.isUpperCase(c) || Character.isLowerCase(c)) {
                if (state == 1) {
                    output += Character.toUpperCase(c);
                    state = 0;
                } else {
                    if (i + 1 < in.length && in[i + 1] == ' ') {
                        output += c;
                    } else {
                        output += Character.toLowerCase(c);
                    }
                }
            } else if (c == ChatColor.COLOR_CHAR) {
                output += c;
            } else {
                state = 1;
                output += c;
            }
        }
        return output;
    }

    private void checkName(AsyncPlayerChatEvent evt) {
        String name = ChatColor.stripColor(evt.getPlayer().getDisplayName());
        if (name.length() > 20) {
            evt.getPlayer().sendMessage(Colorizor.colorize(toggleCase("Your Name Is Very Long! use /nick to shorten it!")));
            evt.getPlayer().sendMessage(Colorizor.colorize(toggleCase("Your Name Is Very Long! use /nick to shorten it!")));
            evt.getPlayer().sendMessage(Colorizor.colorize(toggleCase("Your Name Is Very Long! use /nick to shorten it!")));
        }
    }

    private void toggleCheck(AsyncPlayerChatEvent evt) {
        Player p = evt.getPlayer();
        if (p.hasMetadata("isMessageToggleOn")) {
            List<MetadataValue> meta = p.getMetadata("isMessageToggleOn");
            if (meta.size() >= 1) {
                if (meta.get(0).asBoolean()) {
                    evt.setMessage(toggleCase(evt.getMessage()));
                }
            }
        }
    }
}
