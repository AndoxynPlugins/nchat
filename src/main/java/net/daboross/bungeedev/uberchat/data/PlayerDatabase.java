/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bungeedev.uberchat.data;

import java.util.List;

/**
 *
 * @author daboross
 */
public interface PlayerDatabase {

    public boolean isColorizorEnabled(String username);

    public boolean setColorizorEnabled(String username, boolean enabled);

    public List<MailData> getMails(String username);

    public void addMail(String username, MailDataImpl mail);

    public void clearMails(String username);

    public String getNick(String username);

    public void setNick(String username, String nickname);

    public List<String> getFullNames(String partialName);
}
