/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import net.daboross.bukkitdev.uberchat.UberChatStatics;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class MeExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Please specify an action to describe.");
            sender.sendMessage("Example: /me says hello");
        } else {
            Bukkit.broadcastMessage(String.format(UberChatStatics.ME_FORMAT, sender instanceof Player ? ((Player) sender).getDisplayName() : "Server", UberChatHelpers.arrayToString(args, " ")));
        }
        return true;
    }
}
