/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bungeedev.uberchat.data;

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

    @Override
    public boolean isColorizorEnabled(String username) {
        return false;
    }

    @Override
    public boolean setColorizorEnabled(String username, boolean enabled) {
        return false;
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

    private static class UserData {

        private boolean colorizorEnabled;
        private List<MailDataImpl> mails;
        private String nick;

        private UserData(boolean colorizorEnabled, List<MailDataImpl> mails, String nick) {
            this.colorizorEnabled = colorizorEnabled;
            this.mails = mails;
            this.nick = nick;
        }
    }
}
