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
public class TogglemeExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, Players Only");
            return true;
        }
        Player p = (Player) sender;
        if (PlayerInfoTracker.getTogglemeEnabled(p.getName())) {
            PlayerInfoTracker.setTogglemeEnabled(p.getName(), false);
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Your chat messages are no longer being toggled.");
        } else {
            PlayerInfoTracker.setTogglemeEnabled(p.getName(), true);
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Your future chat messages will now be toggled.");
        }
        return true;
    }
}
