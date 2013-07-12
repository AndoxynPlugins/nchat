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

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 *
 * @author Dabo Ross
 */
public class UberChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {
        e.setMessage(UberChatSensor.getSensoredMessage(e.getMessage()));
        String m = e.getMessage();
        if (e.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer pl = (ProxiedPlayer) e.getSender();
            for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                if (!p.getServer().getInfo().getName().equals(pl.getServer().getInfo().getName())) {
                    p.sendMessage(String.format(UberChatStatics.FORMAT.CHAT, pl.getDisplayName(), m));
                }
            }
        }
    }
}
