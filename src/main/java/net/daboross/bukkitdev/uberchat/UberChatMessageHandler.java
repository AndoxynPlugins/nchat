/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class UberChatMessageHandler {

    public static void sendMessage(CommandSender sender, CommandSender receiver, String message) {
        if (sender instanceof Player) {
            UberChatHelpers.formatPlayerDisplayname((Player) sender);
        }
        if (receiver instanceof Player) {
            UberChatHelpers.formatPlayerDisplayname((Player) receiver);
        }
        String sensoredMessage = UberChatSensor.getSensoredMessage(message, sender);
        String senderName = sender instanceof Player ? ((Player) sender).getDisplayName() : (sender instanceof ConsoleCommandSender ? UberChatStatics.STRINGS.SERVER_NAME : sender.getName());
        String receiverName = receiver instanceof Player ? ((Player) receiver).getDisplayName() : (receiver instanceof ConsoleCommandSender ? UberChatStatics.STRINGS.SERVER_NAME : receiver.getName());
        String messageForSender = String.format(UberChatStatics.FORMAT.MSG, UberChatStatics.STRINGS.MSG_YOU_REPRESENTATION, receiverName, sensoredMessage);
        String messageForReceiver = String.format(UberChatStatics.FORMAT.MSG, senderName, UberChatStatics.STRINGS.MSG_YOU_REPRESENTATION, sensoredMessage);
        String messageForSpy = String.format(UberChatStatics.FORMAT.MSG_SPY, senderName, receiverName, sensoredMessage);
        sender.sendMessage(messageForSender);
        receiver.sendMessage(messageForReceiver);
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission(UberChatStatics.PERMISSION.MSG_SPY) && p != sender && p != receiver) {
                p.sendMessage(messageForSpy);
            }
        }
        CommandSender console = Bukkit.getConsoleSender();
        if (console != sender && console != receiver) {
            console.sendMessage(messageForSpy);
        }
        PlayerInfoTracker.setReplyto(receiver.getName(), sender.getName());
        PlayerInfoTracker.setReplyto(sender.getName(), receiver.getName());
    }
}
