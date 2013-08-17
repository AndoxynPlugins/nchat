/*
 * Copyright (C) 2013 Dabo Ross <www.daboross.net>
 */
package net.daboross.bungeedev.nchat.commandexecutors;

import net.daboross.bungeedev.nchat.ChatSensor;
import net.daboross.bungeedev.nchat.Statics;
import net.daboross.bungeedev.nchat.StringUtils;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.ncommon.utils.ConnectorUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class StaffChatCommand extends Command {

    private final NChatPlugin plugin;

    public StaffChatCommand(NChatPlugin plugin) {
        super("staffchat", null, "sc", "a");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 0) {
            sender.sendMessages(Statics.COLOR.MAIN + "Too many arguments",
                    Statics.COLOR.MAIN + "Usage: /sc");
        } else {
            String message = String.format(Statics.FORMAT.ME,
                    sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : "Server",
                    ChatSensor.getSensoredMessage(StringUtils.arrayToString(args, " ")));
            ProxyServer.getInstance().broadcast(message);
            ConnectorUtils.consoleMessage(message);
        }
    }
}
