/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 *
 * @author daboross
 */
public class UberChatMessageHandler {

    public static void sendMessage(CommandSender sender, CommandSender receiver, String message) {
        String sensoredMessage = UberChatSensor.getSensoredMessage(message, sender);
        String messageForSender = String.format(UberChatStatics.MSG_FORMAT, "me", receiver.getName(), sensoredMessage);
        String messageForReceiver = String.format(UberChatStatics.MSG_FORMAT, sender.getName(), "me", sensoredMessage);
        String messageForSpy = String.format(UberChatStatics.MSG_SPY_FORMAT, sender.getName(), receiver.getName(), sensoredMessage);
        sender.sendMessage(messageForSender);
        receiver.sendMessage(messageForReceiver);
        Bukkit.broadcast(messageForSpy, UberChatStatics.Permission.MSG_SPY);
        PlayerInfoTracker.setReplyto(receiver.getName(), sender.getName());
        PlayerInfoTracker.setReplyto(sender.getName(), receiver.getName());
    }
}
