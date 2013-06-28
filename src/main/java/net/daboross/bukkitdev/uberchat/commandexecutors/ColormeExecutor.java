/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import net.daboross.bukkitdev.uberchat.PlayerInfoTracker;
import net.daboross.bukkitdev.uberchat.UberChatStatics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class ColormeExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Sorry, Players Only");
            return true;
        }
        Player p = (Player) sender;
        if (PlayerInfoTracker.getColormeEnabled(p.getName())) {
            PlayerInfoTracker.setColormeEnabled(p.getName(), false);
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Your chat messages will no longer be colorized.");
        } else {
            PlayerInfoTracker.setColormeEnabled(p.getName(), true);
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Your chat messages will now be colorized.");
        }
        return true;
    }
}
