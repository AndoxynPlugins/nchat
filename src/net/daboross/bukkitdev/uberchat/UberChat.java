package net.daboross.bukkitdev.uberchat;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * UberChat Plugin Made By DaboRoss
 *
 * @author daboross
 */
public final class UberChat extends JavaPlugin {

    private static UberChat currentInstance;

    /**
     *
     */
    @Override
    public void onEnable() {
        currentInstance = this;
        PluginManager pm = this.getServer().getPluginManager();
        /*PluginCommand pd = getCommand("pd");
        PluginCommand gu = getCommand("gu");
        if (pd != null) {
            pd.setExecutor();
        } else {
            getLogger().severe("Command PD is null");
        }*/
        //pm.registerEvents(new PlayerDataEventListener(this), this);
        getLogger().info("UberChat Fully Enabled");
    }

    /**
     *
     */
    @Override
    public void onDisable() {
        currentInstance = null;
        getLogger().info("UberChat Fully Disabled");
    }

    /**
     *
     * @return
     */
    protected static UberChat getCurrentInstance() {
        return currentInstance;
    }
}
