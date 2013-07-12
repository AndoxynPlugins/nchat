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

import java.util.List;
import net.daboross.bungeedev.uberchat.UberChatHelpers;
import net.daboross.bungeedev.uberchat.UberChatMessageHandler;
import net.daboross.bungeedev.uberchat.UberChatStatics;
import net.daboross.bungeedev.uberchat.UberChatUserFinder;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class MsgCommand extends Command {

    public MsgCommand() {
        super("msg", null, "m", "tell", "t");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a user and a message.");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /msg <user> <message> (sends a private <message> to <user>)");
        } else if (args.length == 1) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a message.");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /msg <user> <message> (sends a private <message> to <user>)");
        } else {
            List<ProxiedPlayer> foundUsers = UberChatUserFinder.findUsers(args[0]);
            if (foundUsers.isEmpty()) {
                sender.sendMessage(UberChatStatics.COLOR.MAIN + "User \"" + ChatColor.RED + args[0] + UberChatStatics.COLOR.MAIN + "\" not found or not online.");
            } else if (foundUsers.size() == 1) {
                String message = UberChatHelpers.arrayToString(1, args, " ");
                UberChatMessageHandler.sendMessage(sender, foundUsers.get(0), message);
            } else {
                sender.sendMessage(UberChatStatics.COLOR.MAIN + "Multiple users matching \"" + args[0] + "\":");
                sender.sendMessage(getNameString(foundUsers));
            }
        }
    }

    private static String getNameString(List<? extends CommandSender> list) {
        StringBuilder resultBuilder = new StringBuilder(ChatColor.BLUE.toString()).append(list.get(0).getName());
        for (int i = 1; i < list.size(); i++) {
            resultBuilder.append(ChatColor.BLACK.toString()).append(", ").append(ChatColor.BLUE.toString()).append(list.get(i).getName());
        }
        return resultBuilder.toString();
    }
}