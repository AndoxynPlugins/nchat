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
package net.daboross.bungeedev.nchat;

import lombok.Getter;
import net.daboross.bungeedev.nchat.commands.MeCommand;
import net.daboross.bungeedev.nchat.commands.MsgCommand;
import net.daboross.bungeedev.nchat.commands.NickCommand;
import net.daboross.bungeedev.nchat.commands.ReplyCommand;
import net.daboross.bungeedev.nchat.commands.ShoutCommand;
import net.daboross.bungeedev.nchat.commands.StaffChatCommand;
import net.daboross.bungeedev.nchat.data.DisplayNameDatabase;
import net.daboross.bungeedev.nchat.data.PlayerDatabase;
import net.daboross.bungeedev.nchat.data.PlayerReplyTracker;
import net.daboross.bungeedev.nchat.listeners.ChatListener;
import net.daboross.bungeedev.nchat.listeners.JoinListener;
import net.daboross.bungeedev.nchat.listeners.QuitListener;
import net.daboross.bungeedev.ncommon.NCommonPlugin;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public final class NChatPlugin extends Plugin {

    @Getter
    private PlayerDatabase playerDatabase;
    @Getter
    private MessageHandler messageHandler;
    @Getter
    private DisplayNameDatabase displayNameDatabase;
    @Getter
    private PlayerReplyTracker replyTracker;
    @Getter
    private NCommonPlugin ncommon;

    @Override
    public void onEnable() {
        ncommon = (NCommonPlugin) getProxy().getPluginManager().getPlugin("ncommon");
        replyTracker = new PlayerReplyTracker();
        displayNameDatabase = new DisplayNameDatabase(this);
        messageHandler = new MessageHandler(this);
        playerDatabase = new PlayerDatabase();
        registerStuff();
    }

    @Override
    public void onDisable() {
        ncommon = null; // Just in case
    }

    private void registerStuff() {
        PluginManager pm = getProxy().getPluginManager();
        registerListeners(pm, new ChatListener(this), new JoinListener(this), new QuitListener(this));
        registerCommands(pm, new MeCommand(), new MsgCommand(this), new ReplyCommand(this),
                new ShoutCommand(this), new NickCommand(this), new StaffChatCommand(this));
    }

    private void registerListeners(PluginManager pm, Listener... listeners) {
        for (Listener listener : listeners) {
            pm.registerListener(this, listener);
        }
    }

    private void registerCommands(PluginManager pm, Command... commands) {
        for (Command command : commands) {
            pm.registerCommand(this, command);
        }
    }
}
