/*
 * Copyright (C) 2013 Dabo Ross <www.daboross.net>
 */
package net.daboross.bungeedev.uberchat.commandexecutors;

import net.daboross.bungeedev.uberchat.ChatSensor;
import net.daboross.bungeedev.uberchat.Statics;
import net.daboross.bungeedev.uberchat.StringUtils;
import net.daboross.bungeedev.uberchat.UberChatPlugin;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class StaffChatCommand extends Command {

    private final UberChatPlugin plugin;

    public StaffChatCommand(UberChatPlugin plugin) {
        super("staffchat", null, "sc", "a");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 0) {
            sender.sendMessages(Statics.COLOR.MAIN + "Too many arguments",
                    Statics.COLOR.MAIN + "Usage: /a");
        } else {
            String message = String.format(Statics.FORMAT.ME,
                    sender instanceof ProxiedPlayer ? ((ProxiedPlayer) sender).getDisplayName() : "Server",
                    ChatSensor.getSensoredMessage(StringUtils.arrayToString(args, " ")));
            ProxyServer.getInstance().broadcast(message);
            plugin.getUtils().consoleMessage(message);
        }
    }
}
