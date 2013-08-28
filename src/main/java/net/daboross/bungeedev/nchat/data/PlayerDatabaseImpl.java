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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author daboross
 */
public class PlayerDatabaseImpl implements PlayerDatabase {

    private final Map<String, UserData> dataMap = new HashMap<>();

    public void readData() {
    }

    public void clearPlayer(String username) {
        dataMap.remove(username);
    }

    @Override
    public boolean isColorizorEnabled(String username) {
        UserData data = dataMap.get(username);
        return data != null && data.isColorizorEnabled();
    }

    @Override
    public void setColorizorEnabled(String username, boolean enabled) {
        UserData data = dataMap.get(username);
        if (data == null) {
            data = new UserData();
            dataMap.put(username, data);
        }
        data.setColorizorEnabled(enabled);
    }

    @Override
    public List<MailData> getMails(String username) {
        return null;
    }

    @Override
    public void addMail(String username, MailDataImpl mail) {
    }

    @Override
    public void clearMails(String username) {
    }

    @Override
    public String getNick(String username) {
        return username;
    }

    @Override
    public void setNick(String username, String nickname) {
    }

    @Override
    public List<String> getFullNames(String partialName) {
        String partialNameLowercase = partialName.toLowerCase();
        List<String> fullNames = new ArrayList<>();
        for (Map.Entry<String, UserData> entry : dataMap.entrySet()) {
            if (partialNameLowercase.contains(entry.getKey())) {
                fullNames.add(entry.getKey());
            }
        }
        return fullNames;
    }

    @Override
    public boolean isStaffChatEnabled(String username) {
        UserData data = dataMap.get(username);
        return data != null && data.isStaffChatEnabled();
    }

    @Override
    public void setStaffChatEnabled(String username, boolean enabled) {
        UserData data = dataMap.get(username);
        if (data == null) {
            data = new UserData();
            dataMap.put(username, data);
        }
        data.setStaffChatEnabled(enabled);
    }

    private static class UserData {

        private boolean colorizorEnabled;
        private boolean staffChatEnabled;

        public UserData() {
        }

        public void setColorizorEnabled(boolean colorizorEnabled) {
            this.colorizorEnabled = colorizorEnabled;
        }

        public void setStaffChatEnabled(boolean staffChatEnabled) {
            this.staffChatEnabled = staffChatEnabled;
        }

        public boolean isColorizorEnabled() {
            return colorizorEnabled;
        }

        public boolean isStaffChatEnabled() {
            return staffChatEnabled;
        }
    }
}
