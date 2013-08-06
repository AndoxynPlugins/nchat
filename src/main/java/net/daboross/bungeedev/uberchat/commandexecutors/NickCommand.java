/*
 * Copyright (C) 2013 Dabo Ross <www.daboross.net>
 */
package net.daboross.bungeedev.uberchat.commandexecutors;

import net.daboross.bungeedev.uberchat.UberChatPlugin;
import net.md_5.bungee.api.CommandSender;
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
        sender.sendMessage("Sorry, this doesn't work yet. Coming soon.");
    }
}
