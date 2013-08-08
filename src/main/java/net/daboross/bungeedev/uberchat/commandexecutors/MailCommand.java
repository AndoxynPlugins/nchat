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
package net.daboross.bungeedev.uberchat.commandexecutors;

import net.daboross.bungeedev.uberchat.Statics.COLOR;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class MailCommand extends Command {

    public MailCommand() {
        super("ubermail", null, "umail");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessages(COLOR.MAIN + "Please specify an action.",
                    COLOR.MAIN + "Usage: /mail [send or read or clear]");
        } else {
            switch (args[0].toLowerCase()) {
                case "send":
                    executeSend(sender, args);
                    break;
                case "read":
                    executeRead(sender, args);
                    break;
                case "clear":
                    executeClear(sender, args);
                    break;
                default:
                    sender.sendMessages(COLOR.MAIN + "Unknown mail action: " + args[0],
                            COLOR.MAIN + "Usage: /mail [send or read or clear]");
            }
        }
    }

    private void executeSend(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessages(COLOR.MAIN + "Please specify a recipient and message.",
                    COLOR.MAIN + "Usage: /mail send [recipient] [message]");
        } else if (args.length == 2) {
            sender.sendMessages(COLOR.MAIN + "Please specify a message.",
                    COLOR.MAIN + "Usage: /mail send [recipient] [message]");
        } else {
        }
    }

    private void executeRead(CommandSender sender, String[] args) {
    }

    private void executeClear(CommandSender sender, String[] args) {
    }
}