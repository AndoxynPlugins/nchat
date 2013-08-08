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

import net.daboross.bungeedev.uberchat.UberChatPlugin;
import net.daboross.bungeedev.uberchat.Statics;
import net.daboross.bungeedev.uberchat.data.PlayerDatabaseImpl;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class ColorizorCommand extends Command {

    private final UberChatPlugin uberChat;

    public ColorizorCommand(UberChatPlugin uberChat) {
        super("colorize");
        this.uberChat = uberChat;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(Statics.COLOR.MAIN + "Sorry, Players Only");
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        PlayerDatabaseImpl database = uberChat.getPlayerDatabase();
        if (database.isColorizorEnabled(p.getName())) {
            database.setColorizorEnabled(p.getName(), false);
            sender.sendMessage(Statics.COLOR.MAIN + "Colorizor Enabled");
        } else {
            database.setColorizorEnabled(p.getName(), true);
            sender.sendMessage(Statics.COLOR.MAIN + "Colorizor Disabled");
        }
    }
}
