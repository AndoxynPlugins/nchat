/*
 * Copyright (C) 2013 Dabo Ross <www.daboross.net>
 */
package net.daboross.bungeedev.uberchat.data;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import net.daboross.bungeedev.uberchat.UberChatPlugin;

/**
 *
 * @author daboross
 */
public class DisplayNameDatabase {

    private final UberChatPlugin plugin;
    private File databaseFile;
    private SimpleDatabase database;

    public DisplayNameDatabase(UberChatPlugin plugin) {
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
        return database.get(playerName.toLowerCase());
    }

    public void setDisplayName(String playerName, String displayName) {
        database.set(playerName, displayName.toLowerCase());
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
