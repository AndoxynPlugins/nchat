package net.daboross.bukkitdev.uberchat;

import net.daboross.bukkitdev.uberchat.commandexecutors.MeCommand;
import net.daboross.bukkitdev.uberchat.commandexecutors.MsgCommand;
import net.daboross.bukkitdev.uberchat.commandexecutors.ReplyCommand;
import net.daboross.bukkitdev.uberchat.commandexecutors.ShoutCommand;
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

    @Override
    public void onEnable() {
        UberChatListener uberChatListener = new UberChatListener();
        ProxyServer.getInstance().getPluginManager().registerListener(this, uberChatListener);
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
}
