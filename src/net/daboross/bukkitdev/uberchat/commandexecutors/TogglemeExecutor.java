/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import java.util.List;
import net.daboross.bukkitdev.uberchat.PlayerInfoTracker;
import net.daboross.bukkitdev.uberchat.UberChat;
import net.daboross.bukkitdev.uberchat.UberChatStatics;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

/**
 *
 * @author daboross
 */
public class TogglemeExecutor implements CommandExecutor {

    private final UberChat uberChatMain;

    public TogglemeExecutor(UberChat uberChatMain) {
        this.uberChatMain = uberChatMain;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, Players Only");
            return true;
        }
        Player p = (Player) sender;
        if(PlayerInfoTracker.getTogglemeEnabled(p.getName())){
            PlayerInfoTracker.setTogglemeEnabled(p.getName(), false);
            sender.sendMessage(ChatColor.GREEN + "Your chat messages are no longer being toggled.");
        } else {
            PlayerInfoTracker.setTogglemeEnabled(p.getName(), true);
            sender.sendMessage(ChatColor.GREEN + "Your future chat messages will now be toggled.");
        }
        return true;
    }
}
