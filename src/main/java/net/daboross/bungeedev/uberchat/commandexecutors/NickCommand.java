/*
 * Copyright (C) 2013 Dabo Ross <www.daboross.net>
 */
package net.daboross.bungeedev.uberchat.commandexecutors;

import net.daboross.bungeedev.uberchat.UCStringUtils;
import net.daboross.bungeedev.uberchat.UberChatPlugin;
import net.daboross.bungeedev.uberchat.UberChatSensor;
import net.daboross.bungeedev.uberchat.UberChatStatics;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class NickCommand extends Command {

    private final UberChatPlugin plugin;

    public NickCommand(UberChatPlugin plugin) {
        super("nick");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "You aren't a player.");
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (args.length == 0) {
            sender.sendMessages(UberChatStatics.COLOR.MAIN + "Please specify a new nickname.",
                    UberChatStatics.COLOR.MAIN + "Usage: /nick <Nick name you want>");
        } else {
            String newNick = UberChatSensor.formatPlayerDisplayname(UCStringUtils.arrayToString(args, " "));
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "You're nickname is now " + ChatColor.BLUE + newNick);
            p.setDisplayName(newNick);
            plugin.getDisplayNameDatabase().setDisplayName(p.getName(), newNick);
            plugin.getUtils().setDisplayName(p, newNick);
        }
    }
}
