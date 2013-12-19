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
package net.daboross.bungeedev.nchat.commands;

import lombok.NonNull;
import net.daboross.bungeedev.nchat.ChatSensor;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.nchat.Statics;
import net.daboross.bungeedev.nchat.StringUtils;
import net.daboross.bungeedev.ncommon.ColorList;
import net.daboross.bungeedev.ncommon.utils.ConnectorUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class NickCommand extends Command {

    private final NChatPlugin plugin;

    public NickCommand(@NonNull NChatPlugin plugin) {
        super("nick");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(Statics.COLOR.MAIN + "You aren't a player.");
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (args.length == 0) {
            sender.sendMessages(Statics.COLOR.MAIN + "Please specify a new nickname.", Statics.COLOR.MAIN + "Usage: /nick <Nick name you want>");
        } else {
            String oldNick = p.getDisplayName();
            String newNick = ChatSensor.formatPlayerDisplayname(StringUtils.arrayToString(args, " "));
            p.setDisplayName(newNick);
            plugin.getDisplayNameDatabase().setDisplayName(p.getName(), newNick);
            ConnectorUtils.setDisplayName(p.getServer(), newNick);
            plugin.getProxy().broadcast(ColorList.PREFIX_Q + oldNick + ChatColor.GRAY + " > " + newNick);
        }
    }
}
