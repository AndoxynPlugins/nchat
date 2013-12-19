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
package net.daboross.bungeedev.nchat.listeners;

import java.util.logging.Level;
import net.daboross.bungeedev.nchat.ChatSensor;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.nchat.Statics;
import net.daboross.bungeedev.ncommon.utils.ConnectorUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    private final NChatPlugin plugin;

    public ChatListener(NChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(ChatEvent e) {
        Connection connectionSender = e.getSender();
        if (e.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer sender = (ProxiedPlayer) connectionSender;
            String m = e.getMessage();
            if (m.length() == 0) {
                e.setCancelled(true);
                sender.sendMessage("That message is empty.");
            } else if (m.charAt(0) != '/') {
                if (plugin.getPlayerDatabase().isStaffChatEnabled(sender.getName())) {
                    String message = String.format(Statics.FORMAT.STAFFCHAT, sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : "Server", ChatSensor.getSensoredMessage(m));
                    ConnectorUtils.sendWithPermission("nchat.staffchat", message);
                    plugin.getProxy().getLogger().log(Level.INFO, message);
                    ConnectorUtils.consoleMessage(message);
                } else {
                    String broadcast = String.format(Statics.FORMAT.CHAT, sender.getDisplayName(), ChatSensor.getSensoredMessage(m));
                    ProxyServer.getInstance().broadcast(broadcast);
                    ConnectorUtils.consoleMessage(broadcast);
                }
                e.setCancelled(true);
            }
        } else {
            plugin.getLogger().log(Level.WARNING, "Connection {0} tried to chat while not being a ProxiedPlayer.", connectionSender);
        }
    }
}
