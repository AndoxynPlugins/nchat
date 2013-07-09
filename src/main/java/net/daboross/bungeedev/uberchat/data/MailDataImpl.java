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
public class MailDataImpl implements MailData {

    private final String sender;
    private final String message;

    public MailDataImpl(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSenderName() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
