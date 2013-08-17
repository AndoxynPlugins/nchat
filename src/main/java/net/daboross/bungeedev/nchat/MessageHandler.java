/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bungeedev.nchat;

import net.daboross.bungeedev.nchat.data.PlayerReplyTracker;
import net.daboross.bungeedev.ncommon.utils.ConnectorUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 *
 * @author daboross
 */
public class MessageHandler {

    private final NChatPlugin plugin;

    public MessageHandler(NChatPlugin plugin) {
        this.plugin = plugin;
    }

    public void sendMessage(CommandSender sender, CommandSender receiver, String message) {
        String sensoredMessage = ChatSensor.getSensoredMessage(message);
        String senderName = sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : sender.getName();
        String receiverName = receiver instanceof ProxiedPlayer ? ((ProxiedPlayer) receiver).getDisplayName() : receiver.getName();
        String messageForSender = String.format(Statics.FORMAT.MSG, Statics.STRINGS.MSG_YOU_REPRESENTATION, receiverName, sensoredMessage);
        String messageForReceiver = String.format(Statics.FORMAT.MSG, senderName, Statics.STRINGS.MSG_YOU_REPRESENTATION, sensoredMessage);
        String messageForSpy = String.format(Statics.FORMAT.MSG_SPY, senderName, receiverName, sensoredMessage);
        sender.sendMessage(messageForSender);
        receiver.sendMessage(messageForReceiver);
        if (sender instanceof ProxiedPlayer) {
            if (receiver instanceof ProxiedPlayer) {
                ConnectorUtils.sendWithPermission(Statics.PERMISSION.MSG_SPY, messageForSpy, sender.getName(), receiver.getName());
            } else {
                ConnectorUtils.sendWithPermission(Statics.PERMISSION.MSG_SPY, messageForSpy, sender.getName());
            }
        } else if (receiver instanceof ProxiedPlayer) {
            ConnectorUtils.sendWithPermission(Statics.PERMISSION.MSG_SPY, messageForSpy, receiver.getName());

        } else {
            ConnectorUtils.sendWithPermission(Statics.PERMISSION.MSG_SPY, messageForSpy);
        }
       ConnectorUtils.consoleMessage(messageForSpy);
        for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
            if (p.hasPermission(Statics.PERMISSION.MSG_SPY) && p != sender && p != receiver) {
                p.sendMessage(messageForSpy);
            }
        }
        PlayerReplyTracker.setReplyto(receiver.getName(), sender.getName());
        PlayerReplyTracker.setReplyto(sender.getName(), receiver.getName());
    }
}
