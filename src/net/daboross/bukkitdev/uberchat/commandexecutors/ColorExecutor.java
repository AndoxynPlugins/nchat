/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import net.daboross.bukkitdev.uberchat.Colorizor;
import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class ColorExecutor implements CommandExecutor {

    public ColorExecutor() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Please type a message after /color");
            sender.sendMessage("Example: `/color Hello There`");
            return true;
        }
        String result = "";
        for (int i = 0; i < args.length; i++) {
            String current = ChatColor.stripColor(args[i]);
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
            String name = UberChatHelpers.formatName("Server");
            Bukkit.getServer().broadcastMessage(name + " " + result);
        }
        return true;
    }
}
