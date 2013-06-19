package net.daboross.bukkitdev.uberchat;

import net.daboross.bukkitdev.uberchat.commandexecutors.ColorExecutor;
import net.daboross.bukkitdev.uberchat.commandexecutors.ColormeExecutor;
import net.daboross.bukkitdev.uberchat.commandexecutors.MeExecutor;
import net.daboross.bukkitdev.uberchat.commandexecutors.MsgExecutor;
import net.daboross.bukkitdev.uberchat.commandexecutors.TogglemeExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * UberChat Plugin Made By DaboRoss
 *
 * @author daboross
 */
public final class UberChat extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEvents();
        assignCommands();
        getLogger().info("UberChat Fully Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("UberChat Fully Disabled");
    }

    private void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();
        UberChatListener uberChatListener = new UberChatListener();
        pm.registerEvents(uberChatListener, this);
    }

    private void assignCommands() {
        PluginCommand colorme = getCommand("colorme");
        if (colorme != null) {
            colorme.setExecutor(new ColormeExecutor(this));
        }
        PluginCommand toggleme = getCommand("toggleme");
        if (toggleme != null) {
            toggleme.setExecutor(new TogglemeExecutor(this));
        }
        PluginCommand color = getCommand("color");
        if (color != null) {
            color.setExecutor(new ColorExecutor());
        }
        PluginCommand me = getCommand("me");
        if (me != null) {
            me.setExecutor(new MeExecutor());
        }
        PluginCommand msg = getCommand("msg");
        if (msg != null) {
            msg.setExecutor(new MsgExecutor());
        }
    }
}
