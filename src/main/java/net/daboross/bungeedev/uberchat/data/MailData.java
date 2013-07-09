/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bungeedev.uberchat.data;

/**
 *
 * @author daboross
 */
public interface MailData {

    /**
     * @return the name of the sender of this mail
     */
    public String getSenderName();

    /**
     * @return the message in this mail
     */
    public String getMessage();
}
