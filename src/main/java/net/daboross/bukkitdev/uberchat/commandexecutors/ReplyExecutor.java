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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author daboross
 */
public class ReplyExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a message to send.");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /" + label + " <message> (Sends <message> to the last person who messaged you.)");
        } else {
            String replyToName = PlayerInfoTracker.getReplyto(sender.getName());
            CommandSender replyTo = replyToName == null ? null : UberChatUserFinder.findCommandSenderExact(replyToName);
            if (replyTo == null) {
                sender.sendMessage(UberChatStatics.COLOR.MAIN + "No user found to reply to.");
            } else {
                String message = UberChatHelpers.arrayToString(args, " ");
                UberChatMessageHandler.sendMessage(sender, replyTo, message);
            }
        }
        return true;
    }
}