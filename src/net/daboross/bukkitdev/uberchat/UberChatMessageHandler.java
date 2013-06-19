/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class UberChatMessageHandler {

    public static void sendMessage(CommandSender sender, CommandSender receiver, String message) {
        String sensoredMessage = UberChatSensor.getSensoredMessage(message, sender);
        String senderName = sender instanceof Player ? ((Player) sender).getDisplayName() : sender.getName();
        String receiverName = receiver instanceof Player ? ((Player) receiver).getDisplayName() : receiver.getName();
        String messageForSender = String.format(UberChatStatics.FORMAT.MSG, "me", receiverName, sensoredMessage);
        String messageForReceiver = String.format(UberChatStatics.FORMAT.MSG, senderName, "me", sensoredMessage);
        String messageForSpy = String.format(UberChatStatics.FORMAT.MSG_SPY, senderName, receiverName, sensoredMessage);
        sender.sendMessage(messageForSender);
        receiver.sendMessage(messageForReceiver);
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission(UberChatStatics.PERMISSION.MSG_SPY) && p != sender && p != receiver) {
                p.sendMessage(messageForSpy);
            }
        }
        Bukkit.getConsoleSender().sendMessage(messageForSpy);
        PlayerInfoTracker.setReplyto(receiver.getName(), sender.getName());
        PlayerInfoTracker.setReplyto(sender.getName(), receiver.getName());
    }
}
