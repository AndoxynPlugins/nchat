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

import net.daboross.bungeedev.nchat.ChatSensor;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.nchat.Statics;
import net.daboross.bungeedev.ncommon.ColorList;
import net.daboross.bungeedev.ncommon.utils.CUtils;
import net.daboross.bungeedev.ncommon.utils.NUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ShoutCommand extends Command {

    private final NChatPlugin plugin;

    public ShoutCommand(NChatPlugin plugin) {
        super("shout", null, "sh");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ColorList.REG + "Please specify a message");
            sender.sendMessage(ColorList.REG + "Usage: /sh <message> (shouts <message>)");
        } else {
            String message = String.format(Statics.Format.SHOUT, NUtils.name(sender),
                    ChatSensor.getSensoredMessage(NUtils.arrayToString(args, " ")));
            plugin.getProxy().broadcast(message);
            CUtils.consoleMessage(message);
        }
    }
}
