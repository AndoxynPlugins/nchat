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

import net.daboross.bungeedev.mysqlmap.api.ResultRunnable;
import net.daboross.bungeedev.nchat.ChatSensor;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.ncommon.ColorList;
import net.daboross.bungeedev.ncommon.utils.CUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

    public static final String JOIN_FORMAT = ColorList.PREFIX_Q + "%s" + ChatColor.DARK_GRAY + " (%s" + ChatColor.DARK_GRAY + ")" + ChatColor.GRAY + " joined";
    private final NChatPlugin plugin;

    public JoinListener(NChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinProxy(PostLoginEvent evt) {
        final ProxiedPlayer p = evt.getPlayer();
        plugin.getDisplayNameDatabase().get(p.getName(), new ResultRunnable<String>() {
            @Override
            public void runWithResult(String name) {
                if (name == null) {
                    name = ChatSensor.formatPlayerDisplayname(p.getName());
                }
                p.setDisplayName(name);
                String message = String.format(JOIN_FORMAT, name, p.getName());
                plugin.getProxy().broadcast(message);
                CUtils.consoleMessage(message);
            }
        });
    }

    @EventHandler
    public void onServerConnected(ServerConnectedEvent evt) {
        final ProxiedPlayer p = evt.getPlayer();
        CUtils.setDisplayName(evt.getServer(), p.getDisplayName());
    }
}
