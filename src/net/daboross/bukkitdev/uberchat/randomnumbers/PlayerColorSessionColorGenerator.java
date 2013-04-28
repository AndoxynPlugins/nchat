package net.daboross.bukkitdev.uberchat.randomnumbers;

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
public class PlayerColorSessionColorGenerator implements Listener {

    private final PlayerColorStorage playerColorStorage;

    public PlayerColorSessionColorGenerator(PlayerColorStorage playerColorStorage) {
        this.playerColorStorage = playerColorStorage;
    }

    public void initalLoad() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            playerColorStorage.newSet(p.getName());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(final PlayerJoinEvent evt) {
        playerColorStorage.newSet(evt.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(final PlayerQuitEvent evt) {
        playerColorStorage.removeSet(evt.getPlayer().getName());
    }
}
