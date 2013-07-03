/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import net.daboross.bukkitdev.uberchat.UberChatSensor;
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
public class ShoutExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a message");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /" + label + " <message> (shouts <message>)");
        } else {
            Bukkit.broadcastMessage(String.format(UberChatStatics.FORMAT.SHOUT,
                    sender instanceof Player ? ((Player) sender).getDisplayName() : "Server",
                    UberChatSensor.getSensoredMessage(UberChatHelpers.arrayToString(args, " "), sender)));
        }
        return true;
    }
}
