/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bungeedev.uberchat;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 *
 * @author daboross
 */
public class UberChatMessageHandler {

    public static void sendMessage(CommandSender sender, CommandSender receiver, String message) {
        if (sender instanceof ProxiedPlayer) {
            UberChatHelpers.formatPlayerDisplayname((ProxiedPlayer) sender);
        }
        if (receiver instanceof ProxiedPlayer) {
            UberChatHelpers.formatPlayerDisplayname((ProxiedPlayer) receiver);
        }
        String sensoredMessage = UberChatSensor.getSensoredMessage(message);
        String senderName = sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : sender.getName();
        String receiverName = receiver instanceof ProxiedPlayer ? ((ProxiedPlayer) receiver).getDisplayName() : receiver.getName();
        String messageForSender = String.format(UberChatStatics.FORMAT.MSG, UberChatStatics.STRINGS.MSG_YOU_REPRESENTATION, receiverName, sensoredMessage);
        String messageForReceiver = String.format(UberChatStatics.FORMAT.MSG, senderName, UberChatStatics.STRINGS.MSG_YOU_REPRESENTATION, sensoredMessage);
        String messageForSpy = String.format(UberChatStatics.FORMAT.MSG_SPY, senderName, receiverName, sensoredMessage);
        sender.sendMessage(messageForSender);
        receiver.sendMessage(messageForReceiver);
        for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
            if (p.hasPermission(UberChatStatics.PERMISSION.MSG_SPY) && p != sender && p != receiver) {
                p.sendMessage(messageForSpy);
            }
        }
        PlayerInfoTracker.setReplyto(receiver.getName(), sender.getName());
        PlayerInfoTracker.setReplyto(sender.getName(), receiver.getName());
    }
}
