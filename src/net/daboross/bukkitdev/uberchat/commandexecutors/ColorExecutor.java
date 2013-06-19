/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import net.daboross.bukkitdev.uberchat.Colorizor;
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
public class ColorExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please type a message after /color");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Example: `/color Hello world`");
            return true;
        }
        String result = "";
        for (int i = 0; i < args.length; i++) {
            String current = args[i];
            if (!result.equals("")) {
                result += " ";
            }
            if (current.startsWith("/")) {
                result += current;
            } else if (current.startsWith("\\")) {
                result += current.substring(1, current.length());
            } else {
                result += Colorizor.getColorString(current);
            }
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.chat(result);
        } else {
            Bukkit.getServer().broadcastMessage(String.format(UberChatStatics.FORMAT.ANNOUNCER, "Server", result));
        }
        return true;
    }
}
