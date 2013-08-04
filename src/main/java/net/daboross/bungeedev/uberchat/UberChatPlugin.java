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

import net.daboross.bungeedev.uberchat.commandexecutors.MeCommand;
import net.daboross.bungeedev.uberchat.commandexecutors.MsgCommand;
import net.daboross.bungeedev.uberchat.commandexecutors.ReplyCommand;
import net.daboross.bungeedev.uberchat.commandexecutors.ShoutCommand;
import net.daboross.bungeedev.uberchat.data.PlayerDatabaseImpl;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginManager;

/**
 * UberChatPlugin Plugin Made By DaboRoss
 *
 * @author daboross
 */
public final class UberChatPlugin extends Plugin {

    private PlayerDatabaseImpl playerDatabase;

    @Override
    public void onEnable() {
        UberChatListener uberChatListener = new UberChatListener(this);
        ProxyServer.getInstance().getPluginManager().registerListener(this, uberChatListener);
        assignCommands();
        getLogger().info("UberChat Fully Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("UberChat Fully Disabled");
    }

    private void assignCommands() {
        PluginManager pm = getProxy().getPluginManager();
        Command me = new MeCommand();
        pm.registerCommand(this, me);
        Command msg = new MsgCommand();
        pm.registerCommand(this, msg);
        Command reply = new ReplyCommand();
        pm.registerCommand(this, reply);
        Command shout = new ShoutCommand();
        pm.registerCommand(this, shout);
    }

    public PlayerDatabaseImpl getPlayerDatabase() {
        return playerDatabase;
    }
}
