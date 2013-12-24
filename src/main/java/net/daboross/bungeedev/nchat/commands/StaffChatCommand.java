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

import java.util.logging.Level;
import net.daboross.bungeedev.mysqlmap.api.ResultRunnable;
import net.daboross.bungeedev.nchat.ChatSensor;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.nchat.Statics;
import net.daboross.bungeedev.nchat.StringUtils;
import net.daboross.bungeedev.nchat.data.PlayerDatabase;
import net.daboross.bungeedev.ncommon.ColorList;
import net.daboross.bungeedev.ncommon.utils.ConnectorUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatCommand extends Command {

    private final NChatPlugin plugin;

    public StaffChatCommand(NChatPlugin plugin) {
        super("staffchat", null, "sc", "a");
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            if (args.length == 0) {
                sender.sendMessage("Not enough arguments");
                return;
            }
            String message = String.format(Statics.Format.STAFFCHAT, sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : "!Server", ChatSensor.getSensoredMessage(StringUtils.arrayToString(args, " ")));
            ConnectorUtils.sendWithPermission(Statics.Permission.STAFF_CHAT, message);
            plugin.getProxy().getLogger().log(Level.INFO, message);
            ConnectorUtils.consoleMessage(message);
            return;
        }
        if (args.length != 0) {
            sender.sendMessages(ColorList.REG + "Too many arguments", ColorList.REG + "Usage: /sc");
            return;
        }
        ConnectorUtils.runWithPermission(((ProxiedPlayer) sender).getServer(), Statics.Permission.STAFF_CHAT, new ResultRunnable<Boolean>() {
            @Override
            public void runWithResult(Boolean value) {
                if (value) {
                    PlayerDatabase database = plugin.getPlayerDatabase();
                    if (database.isStaffChatEnabled(sender.getName())) {
                        database.setStaffChatEnabled(sender.getName(), false);
                        sender.sendMessage(ColorList.REG + "StaffChat Disabled");
                    } else {
                        database.setStaffChatEnabled(sender.getName(), true);
                        sender.sendMessage(ColorList.REG + "StaffChat Enabled");
                    }
                } else {
                    sender.sendMessage(ColorList.ERR + "You don't have permission to use staff chat.");
                }
            }
        });
    }
}
