/*
 * Copyright (C) 2013-2014 Dabo Ross <http://www.daboross.net/>
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
import net.daboross.bungeedev.nchat.data.PlayerDatabase;
import net.daboross.bungeedev.ncommon.utils.CUtils;
import net.daboross.bungeedev.ncommon.utils.NUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import static net.daboross.bungeedev.ncommon.ColorList.*;

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
            String message = String.format(Statics.Format.STAFFCHAT, NUtils.name(sender),
                    ChatSensor.getSensoredMessage(NUtils.arrayToString(args, " ")));
            CUtils.sendWithPermission(Statics.Permission.STAFF_CHAT, message);
            plugin.getProxy().getLogger().log(Level.INFO, message);
            CUtils.consoleMessage(message);
            return;
        }
        if (args.length != 0) {
            sender.sendMessages(REG + "Too many arguments", REG + "Usage: /a");
            return;
        }
        CUtils.runWithPermission(((ProxiedPlayer) sender).getServer(), Statics.Permission.STAFF_CHAT, new ResultRunnable<Boolean>() {
            @Override
            public void runWithResult(Boolean value) {
                if (value) {
                    PlayerDatabase database = plugin.getPlayerDatabase();
                    if (database.staffChatEnabled(sender.getName())) {
                        database.staffChatEnabled(sender.getName(), false);
                        sender.sendMessage(REG + "StaffChat Disabled");
                    } else {
                        database.staffChatEnabled(sender.getName(), true);
                        sender.sendMessage(REG + "StaffChat Enabled");
                    }
                } else {
                    sender.sendMessage(ERR + "You don't have permission to use staff chat.");
                }
            }
        });
    }
}
