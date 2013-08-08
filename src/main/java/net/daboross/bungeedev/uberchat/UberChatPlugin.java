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
package net.daboross.bungeedev.uberchat;

import net.daboross.bungeedev.uberchat.commandexecutors.ColorizorCommand;
import net.daboross.bungeedev.uberchat.commandexecutors.MeCommand;
import net.daboross.bungeedev.uberchat.commandexecutors.MsgCommand;
import net.daboross.bungeedev.uberchat.commandexecutors.NickCommand;
import net.daboross.bungeedev.uberchat.commandexecutors.ReplyCommand;
import net.daboross.bungeedev.uberchat.commandexecutors.ShoutCommand;
import net.daboross.bungeedev.uberchat.data.DisplayNameDatabase;
import net.daboross.bungeedev.uberchat.data.PlayerDatabaseImpl;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginManager;

/**
 * UberChatPlugin Plugin Made By DaboRoss
 *
 * @author daboross
 */
public final class UberChatPlugin extends Plugin {

    private PlayerDatabaseImpl playerDatabase;
    private ConnectorUtils utils;
    private MessageHandler messageHandler;
    private DisplayNameDatabase displayNameDatabase;

    @Override
    public void onEnable() {
        displayNameDatabase = new DisplayNameDatabase(this);
        utils = new ConnectorUtils(this);
        messageHandler = new MessageHandler(this);
        ChatListener uberChatListener = new ChatListener(this);
        getProxy().getPluginManager().registerListener(this, uberChatListener);
        assignCommands();
        getProxy().registerChannel("UberChat");
        getLogger().info("UberChat Fully Enabled");
    }

    @Override
    public void onDisable() {
        displayNameDatabase.save();
        getLogger().info("UberChat Fully Disabled");
    }

    private void assignCommands() {
        PluginManager pm = getProxy().getPluginManager();
        Command me = new MeCommand(this);
        pm.registerCommand(this, me);
        Command msg = new MsgCommand(this);
        pm.registerCommand(this, msg);
        Command reply = new ReplyCommand(this);
        pm.registerCommand(this, reply);
        Command shout = new ShoutCommand(this);
        pm.registerCommand(this, shout);
        Command colorize = new ColorizorCommand(this);
        pm.registerCommand(this, colorize);
        Command nick = new NickCommand(this);
        pm.registerCommand(this, nick);
        pm.registerListener(this, new JoinListener(this));
    }

    public DisplayNameDatabase getDisplayNameDatabase() {
        return displayNameDatabase;
    }

    public PlayerDatabaseImpl getPlayerDatabase() {
        return playerDatabase;
    }

    public ConnectorUtils getUtils() {
        return utils;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
}
