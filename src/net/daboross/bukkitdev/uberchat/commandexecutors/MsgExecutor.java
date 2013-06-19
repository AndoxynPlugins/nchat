/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import java.util.List;
import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import net.daboross.bukkitdev.uberchat.UberChatMessageHandler;
import net.daboross.bukkitdev.uberchat.UberChatUserFinder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author daboross
 */
public class MsgExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Please specify a username and a message.");
            sender.sendMessage("Usage: /" + label + " <user> <message> (sends <message> to <user>)");
        } else if (args.length == 1) {
            sender.sendMessage("Please specify a message.");
            sender.sendMessage("Usage: /" + label + " <user> <message> (sends <message> to <user>)");
        } else {
            List<CommandSender> foundUsers = UberChatUserFinder.findCommandSenders(args[0]);
            if (foundUsers.isEmpty()) {
                sender.sendMessage("User \"" + args[0] + "\" not found");
            } else if (foundUsers.size() == 1) {
                String message = UberChatHelpers.arrayToString(1, args, " ");
                UberChatMessageHandler.sendMessage(sender, foundUsers.get(0), message);
            } else {
                sender.sendMessage("Multiple users matching \"" + args[0] + "\":");
                sender.sendMessage(UberChatHelpers.arrayToString(1, args, ", "));
            }
        }
        return true;
    }
}