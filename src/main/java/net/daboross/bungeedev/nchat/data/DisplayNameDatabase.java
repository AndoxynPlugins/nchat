/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
