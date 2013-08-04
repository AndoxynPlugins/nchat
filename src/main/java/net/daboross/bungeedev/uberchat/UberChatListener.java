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
package net.daboross.bungeedev.uberchat;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 *
 * @author Dabo Ross
 */
public class UberChatListener implements Listener {

    private final UberChatPlugin plugin;

    public UberChatListener(UberChatPlugin plugin) {
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
                String broadcast = String.format(UberChatStatics.FORMAT.CHAT, sender.getDisplayName(), UberChatSensor.getSensoredMessage(m));
                ProxyServer.getInstance().broadcast(broadcast);
                plugin.getLogger().log(Level.INFO, broadcast);
                try {
                    sendPluginMessage(broadcast);
                } catch (IOException ex) {
                    plugin.getLogger().log(Level.SEVERE, "Error sending plugin message", ex);
                }
                e.setCancelled(true);
            }
        } else {
            plugin.getLogger().log(Level.WARNING, "Connection {0} tried to chat while not being a ProxiedPlayer.", connectionSender);
        }
    }

    private void sendPluginMessage(String broadcast) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try (DataOutputStream out = new DataOutputStream(b)) {
            out.writeUTF("ConsoleMessage");
            out.writeUTF(broadcast);
        }
        byte[] data = b.toByteArray();
        System.out.println("Looping through servers.");
        for (Map.Entry<String, ServerInfo> server : ProxyServer.getInstance().getServers().entrySet()) {
            server.getValue().sendData("UberChat", data);
            System.out.println("Sending message to " + server.getKey());
        }
    }
}
