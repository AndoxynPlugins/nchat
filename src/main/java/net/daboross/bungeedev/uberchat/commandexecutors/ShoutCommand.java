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
package net.daboross.bungeedev.uberchat.commandexecutors;

import net.daboross.bungeedev.uberchat.UCStringUtils;
import net.daboross.bungeedev.uberchat.UberChatPlugin;
import net.daboross.bungeedev.uberchat.UberChatSensor;
import net.daboross.bungeedev.uberchat.UberChatStatics;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class ShoutCommand extends Command {

    private final UberChatPlugin plugin;

    public ShoutCommand(UberChatPlugin plugin) {
        super("shout", null, "sh");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a message");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /sh <message> (shouts <message>)");
        } else {
            String message = String.format(UberChatStatics.FORMAT.SHOUT,
                    sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : "Server",
                    UberChatSensor.getSensoredMessage(UCStringUtils.arrayToString(args, " ")));
            ProxyServer.getInstance().broadcast(message);
            plugin.getUtils().consoleMessage(message);
        }
    }
}
