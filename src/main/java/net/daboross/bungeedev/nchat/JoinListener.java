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
package net.daboross.bungeedev.nchat;

import net.daboross.bungeedev.ncommon.utils.ConnectorUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 *
 * @author daboross
 */
public class JoinListener implements Listener {

    private final NChatPlugin plugin;

    public JoinListener(NChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(ServerConnectedEvent evt) {
        final ProxiedPlayer p = evt.getPlayer();
        String name = plugin.getDisplayNameDatabase().getDisplayName(p.getName());
        if (name == null) {
            name = ChatSensor.formatPlayerDisplayname(name);
        }
        p.setDisplayName(name);
        ConnectorUtils.setDisplayName(evt.getServer(), name);
    }
}
