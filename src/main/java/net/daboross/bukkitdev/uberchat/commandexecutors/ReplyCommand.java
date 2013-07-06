/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import net.daboross.bukkitdev.uberchat.PlayerInfoTracker;
import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import net.daboross.bukkitdev.uberchat.UberChatMessageHandler;
import net.daboross.bukkitdev.uberchat.UberChatStatics;
import net.daboross.bukkitdev.uberchat.UberChatUserFinder;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class ReplyCommand extends Command {

    public ReplyCommand() {
        super("reply", null, "r");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a message to send.");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /r <message> (Sends <message> to the last person who messaged you.)");
        } else {
            String replyToName = PlayerInfoTracker.getReplyto(sender.getName());
            CommandSender replyTo = replyToName == null ? null : ProxyServer.getInstance().getPlayer(replyToName);
            if (replyTo == null) {
                sender.sendMessage(UberChatStatics.COLOR.MAIN + "No user found to reply to.");
            } else {
                String message = UberChatHelpers.arrayToString(args, " ");
                UberChatMessageHandler.sendMessage(sender, replyTo, message);
            }
        }
    }
}