/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import net.daboross.bukkitdev.uberchat.UberChatSensor;
import net.daboross.bukkitdev.uberchat.UberChatStatics;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class ShoutCommand extends Command {

    public ShoutCommand() {
        super("shout", null, "sh");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a message");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /sh <message> (shouts <message>)");
        } else {
            ProxyServer.getInstance().broadcast(String.format(UberChatStatics.FORMAT.SHOUT,
                    sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : "Server",
                    UberChatSensor.getSensoredMessage(UberChatHelpers.arrayToString(args, " "))));
        }
    }
}
