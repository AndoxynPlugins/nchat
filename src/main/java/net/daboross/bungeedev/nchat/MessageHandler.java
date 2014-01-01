/*
 * Copyright (C) 2013-2014 Dabo Ross <http://www.daboross.net/>
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

import lombok.RequiredArgsConstructor;
import net.daboross.bungeedev.ncommon.utils.CUtils;
import net.daboross.bungeedev.ncommon.utils.NUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@RequiredArgsConstructor
public class MessageHandler {

    private final NChatPlugin plugin;

    public void sendPrivateMessage(CommandSender sender, CommandSender receiver, String message) {
        message = ChatSensor.getSensoredMessage(message);
        boolean senderPlayer = sender instanceof ProxiedPlayer, receiverPlayer = receiver instanceof ProxiedPlayer;
        String senderName = NUtils.name(sender), receiverName = NUtils.name(receiver);

        sender.sendMessage(String.format(Statics.Format.MSG, Statics.Strings.MSG_YOU_REPRESENTATION, receiverName, message));
        receiver.sendMessage(String.format(Statics.Format.MSG, senderName, Statics.Strings.MSG_YOU_REPRESENTATION, message));

        String spyMessage = String.format(Statics.Format.MSG_SPY, senderName, receiverName, message);

        String[] exclude = senderPlayer ? (receiverPlayer ? new String[]{sender.getName(), receiver.getName()}
                : new String[]{sender.getName()})
                : (receiverPlayer ? new String[]{receiver.getName()} : new String[0]);

        CUtils.sendWithPermission(Statics.Permission.MSG_SPY, spyMessage, exclude);
        CUtils.consoleMessage(spyMessage);

        plugin.getReplyTracker().setReplyRecords(sender.getName(), receiver.getName());
    }
}
