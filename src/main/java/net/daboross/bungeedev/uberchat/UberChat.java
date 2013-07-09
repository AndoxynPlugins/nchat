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
 * UberChat Plugin Made By DaboRoss
 *
 * @author daboross
 */
public final class UberChat extends Plugin {

    private PlayerDatabaseImpl playerDatabase;

    @Override
    public void onEnable() {
        UberChatListener uberChatListener = new UberChatListener();
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
