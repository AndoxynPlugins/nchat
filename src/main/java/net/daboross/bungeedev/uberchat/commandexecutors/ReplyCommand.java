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

import net.daboross.bungeedev.uberchat.data.PlayerReplyTracker;
import net.daboross.bungeedev.uberchat.UCStringUtils;
import net.daboross.bungeedev.uberchat.UberChatMessageHandler;
import net.daboross.bungeedev.uberchat.UberChatPlugin;
import net.daboross.bungeedev.uberchat.UberChatStatics;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class ReplyCommand extends Command {

    private final UberChatPlugin plugin;

    public ReplyCommand(UberChatPlugin plugin) {
        super("reply", null, "r");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a message to send.");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /r <message> (Sends <message> to the last person who messaged you.)");
        } else {
            String replyToName = PlayerReplyTracker.getReplyto(sender.getName());
            CommandSender replyTo = replyToName == null ? null : ProxyServer.getInstance().getPlayer(replyToName);
            if (replyTo == null) {
                sender.sendMessage(UberChatStatics.COLOR.MAIN + "No user found to reply to.");
            } else {
                String message = UCStringUtils.arrayToString(args, " ");
                plugin.getMessageHandler().sendMessage(sender, replyTo, message);
            }
        }
    }
}