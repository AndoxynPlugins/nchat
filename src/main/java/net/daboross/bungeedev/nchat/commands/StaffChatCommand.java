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

import net.daboross.bungeedev.nchat.Statics;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.nchat.data.PlayerDatabase;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class StaffChatCommand extends Command {

    private final NChatPlugin plugin;

    public StaffChatCommand(NChatPlugin plugin) {
        super("staffchat", null, "sc", "a");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(Statics.COLOR.MAIN + "Sorry, Players Only");
        }
        if (args.length != 0) {
            sender.sendMessages(Statics.COLOR.MAIN + "Too many arguments", Statics.COLOR.MAIN + "Usage: /sc");
            return;
        }
        PlayerDatabase database = plugin.getPlayerDatabase();
        if (database.isStaffChatEnabled(sender.getName())) {
            database.setStaffChatEnabled(sender.getName(), false);
            sender.sendMessage(Statics.COLOR.MAIN + "StaffChat Disabled");
        } else {
            database.setStaffChatEnabled(sender.getName(), true);
            sender.sendMessage(Statics.COLOR.MAIN + "StaffChat Enabled");
        }
    }
}
