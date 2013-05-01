package net.daboross.bukkitdev.uberchat;

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
        PluginManager pm = this.getServer().getPluginManager();
        UberChatCommandExecutor uberChatCommandExecutor = new UberChatCommandExecutor(this);
        UberChatListener uberChatListener = new UberChatListener();
        pm.registerEvents(uberChatListener, this);
        PluginCommand colorme = getCommand("colorme");
        PluginCommand toggleme = getCommand("toggleme");
        PluginCommand color = getCommand("color");
        if (colorme != null) {
            colorme.setExecutor(uberChatCommandExecutor);
        }
        if (toggleme != null) {
            toggleme.setExecutor(uberChatCommandExecutor);
        }
        if (color != null) {
            color.setExecutor(uberChatCommandExecutor);
        }
        getLogger().info("UberChat Fully Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("UberChat Fully Disabled");
    }
}
