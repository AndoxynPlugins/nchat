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

import net.daboross.bungeedev.nchat.ChatSensor;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.ncommon.ColorList;
import net.daboross.bungeedev.ncommon.utils.ConnectorUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

    private final NChatPlugin plugin;

    public JoinListener(NChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(ServerConnectedEvent evt) {
        final ProxiedPlayer p = evt.getPlayer();
        ConnectorUtils.setDisplayName(evt.getServer(), p.getDisplayName());
    }

    @EventHandler
    public void onJoinServer(PostLoginEvent evt) {
        final ProxiedPlayer p = evt.getPlayer();
        String name = plugin.getDisplayNameDatabase().getDisplayName(p.getName());
        if (name == null) {
            name = ChatSensor.formatPlayerDisplayname(p.getName());
        }
        p.setDisplayName(name);
        plugin.getProxy().broadcast(ColorList.PREFIX_Q + p.getName() + ChatColor.GRAY + " > " + name);
    }
}
