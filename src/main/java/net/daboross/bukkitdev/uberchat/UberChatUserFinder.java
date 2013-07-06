/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 *
 * @author daboross
 */
public class UberChatUserFinder {

    public static List<ProxiedPlayer> findUsers(String partialUser) {
        Collection<ProxiedPlayer> online = ProxyServer.getInstance().getPlayers();
        List<ProxiedPlayer> result = new ArrayList<ProxiedPlayer>();
        for (ProxiedPlayer p : online) {
            if (p.getName().equals(partialUser)) {
                result.clear();
                result.add(p);
                return result;
            } else if (p.getName().contains(partialUser)) {
                result.add(p);
            } else {
                String display = ChatColor.stripColor(p.getDisplayName());
                if (display.contains(partialUser)) {
                    result.add(p);
                }
            }
        }
        return result;
    }
}
