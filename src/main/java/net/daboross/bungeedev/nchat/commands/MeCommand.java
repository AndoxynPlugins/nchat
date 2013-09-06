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
import net.daboross.bungeedev.nchat.StringUtils;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.nchat.ChatSensor;
import net.daboross.bungeedev.nchat.Statics;
import net.daboross.bungeedev.ncommon.utils.ConnectorUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class MeCommand extends Command {

    private final NChatPlugin plugin;

    public MeCommand(@NonNull NChatPlugin plugin) {
        super("me");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessages(Statics.COLOR.MAIN + "Please specify an action to describe.",
                    Statics.COLOR.MAIN + "Usage: /me <action> (publicly describes you doing <action>)");
        } else {
            String message = String.format(Statics.FORMAT.ME,
                    sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : "Server",
                    ChatSensor.getSensoredMessage(StringUtils.arrayToString(args, " ")));
            ProxyServer.getInstance().broadcast(message);
            ConnectorUtils.consoleMessage(message);
        }
    }
}
