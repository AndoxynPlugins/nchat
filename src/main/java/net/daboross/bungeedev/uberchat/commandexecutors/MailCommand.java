/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bungeedev.uberchat.commandexecutors;

import net.daboross.bungeedev.uberchat.UberChatStatics.COLOR;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
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