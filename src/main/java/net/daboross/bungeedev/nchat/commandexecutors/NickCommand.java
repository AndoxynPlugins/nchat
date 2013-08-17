/*
 * Copyright (C) 2013 Dabo Ross <www.daboross.net>
 */
package net.daboross.bungeedev.nchat.commandexecutors;

import net.daboross.bungeedev.nchat.StringUtils;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.daboross.bungeedev.nchat.ChatSensor;
import net.daboross.bungeedev.nchat.Statics;
import net.daboross.bungeedev.ncommon.utils.ConnectorUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class NickCommand extends Command {

    private final NChatPlugin plugin;

    public NickCommand(NChatPlugin plugin) {
        super("nick");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(Statics.COLOR.MAIN + "You aren't a player.");
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (args.length == 0) {
            sender.sendMessages(Statics.COLOR.MAIN + "Please specify a new nickname.",
                    Statics.COLOR.MAIN + "Usage: /nick <Nick name you want>");
        } else {
            String newNick = ChatSensor.formatPlayerDisplayname(StringUtils.arrayToString(args, " "));
            sender.sendMessage(Statics.COLOR.MAIN + "You're nickname is now " + ChatColor.BLUE + newNick);
            p.setDisplayName(newNick);
            plugin.getDisplayNameDatabase().setDisplayName(p.getName(), newNick);
            ConnectorUtils.setDisplayName(p.getServer(), newNick);
        }
    }
}
