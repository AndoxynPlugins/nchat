package net.daboross.bukkitdev.uberchat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author daboross
 */
public class PlayerColorzListener implements Listener {

    private final UberChatClassDatabase database;

    public PlayerColorzListener(UberChatClassDatabase database) {
        this.database = database;
    }

    public void initalLoad() {
        PlayerColorz pc = database.getPlayerColorz();
        for (Player p : Bukkit.getOnlinePlayers()) {
            pc.newSet(p.getName());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(final PlayerJoinEvent evt) {
        database.getPlayerColorz().newSet(evt.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(final PlayerQuitEvent evt) {
        database.getPlayerColorz().removeSet(evt.getPlayer().getName());
    }
}
