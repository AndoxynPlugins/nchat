package net.daboross.bukkitdev.uberchat;

import java.util.List;
import org.bukkit.Bukkit;
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
public class UberChatCommandExecutor implements CommandExecutor {

    private final UberChat main;

    public UberChatCommandExecutor(UberChat main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("colorme")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Sorry, Players Only");
                return true;
            }
            Player p = (Player) sender;
            boolean on = false;
            if (p.hasMetadata("isMessageColorOn")) {
                List<MetadataValue> meta = p.getMetadata("isMessageColorOn");
                if (meta.size() >= 1 && (meta.get(0).asBoolean())) {
                    on = true;
                }
            }
            if (on) {
                p.removeMetadata("isMessageColorOn", main);
                sender.sendMessage(ChatColor.GREEN + "Your chat messages are no longer being colorized.");
            } else {
                p.setMetadata("isMessageColorOn", new FixedMetadataValue(main, Boolean.TRUE));
                sender.sendMessage(ChatColor.GREEN + "Your future chat messages will now be colorized.");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("toggleme")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Sorry, Players Only");
                return true;
            }
            Player p = (Player) sender;
            boolean on = false;
            if (p.hasMetadata("isMessageToggleOn")) {
                List<MetadataValue> meta = p.getMetadata("isMessageToggleOn");
                if (meta.size() >= 1 && (meta.get(0).asBoolean())) {
                    on = true;
                }
            }
            if (on) {
                p.removeMetadata("isMessageToggleOn", main);
                sender.sendMessage(ChatColor.GREEN + "Your chat messages are no longer being toggled.");
            } else {
                p.setMetadata("isMessageToggleOn", new FixedMetadataValue(main, Boolean.TRUE));
                sender.sendMessage(ChatColor.GREEN + "Your future chat messages will now be toggled.");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("color")) {
            if (args.length < 0) {
                sender.sendMessage("Please type a message after /color");
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
                    result += Colorizor.colorize(current);
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
        return false;
    }
}
