/*
 * Copyright (C) 2013 Dabo Ross <www.daboross.net>
 */
package net.daboross.bungeedev.nchat.data;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import net.daboross.bungeedev.nchat.NChatPlugin;
import net.md_5.bungee.api.ChatColor;

/**
 *
 * @author daboross
 */
public class DisplayNameDatabase {

    private final NChatPlugin plugin;
    private File databaseFile;
    private SimpleDatabase database;

    public DisplayNameDatabase(NChatPlugin plugin) {
        this.plugin = plugin;
        load0();
    }

    private void load0() {
        if (databaseFile == null) {
            databaseFile = new File(plugin.getDataFolder(), "display-names-database");
        }
        if (databaseFile.exists()) {
            try {
                database = new SimpleDatabase(databaseFile);
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Error reading database file.", ex);
            }
        } else {
            database = new SimpleDatabase();
        }
    }

    public void load() {
        load0();
    }

    public String getDisplayName(String playerName) {
        String name = database.get(playerName.toLowerCase());
        return name == null ? ChatColor.BLUE + playerName : name;
    }

    public void setDisplayName(String playerName, String displayName) {
        database.set(playerName.toLowerCase(), displayName);
    }

    public void save() {
        if (databaseFile == null) {
            databaseFile = new File(plugin.getDataFolder(), "display-names-database");
        }
        try {
            database.save(databaseFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Error saving file.", ex);
        }
    }
}
