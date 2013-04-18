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
        UberChatClassDatabase database = new UberChatClassDatabase();
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(database.getPlayerColorzListener(), this);
        pm.registerEvents(new UberChatListener(database), this);
        UberChatCommandExecutor executor = new UberChatCommandExecutor(this, database);
        PluginCommand colorme = getCommand("colorme");
        PluginCommand toggleme = getCommand("toggleme");
        PluginCommand color = getCommand("color");
        if (colorme != null) {
            colorme.setExecutor(executor);
        }
        if (toggleme != null) {
            toggleme.setExecutor(executor);
        }
        if (color != null) {
            color.setExecutor(executor);
        }
        getLogger().info("UberChat Fully Enabled");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("UberChat Fully Disabled");
    }
}
