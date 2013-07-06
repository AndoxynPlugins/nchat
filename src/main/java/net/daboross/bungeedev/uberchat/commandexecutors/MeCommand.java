/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bungeedev.uberchat.commandexecutors;

import net.daboross.bungeedev.uberchat.UberChatHelpers;
import net.daboross.bungeedev.uberchat.UberChatSensor;
import net.daboross.bungeedev.uberchat.UberChatStatics;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class MeCommand extends Command {

    public MeCommand() {
        super("me");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify an action to describe.");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /me <action> (publicly describes you doing <action>)");
        } else {
            ProxyServer.getInstance().broadcast(String.format(UberChatStatics.FORMAT.ME,
                    sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : "Server",
                    UberChatSensor.getSensoredMessage(UberChatHelpers.arrayToString(args, " "))));
        }
    }
}
