/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bungeedev.uberchat.data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author daboross
 */
public class PlayerReplyTracker {

    private static final Map<String, String> replytoMap = new HashMap<>();

    public static void setReplyto(String username, String replyto) {
        replytoMap.put(username, replyto);
    }

    public static void removeReplyTo(String username) {
        replytoMap.remove(username);
    }

    public static String getReplyto(String username) {
        return replytoMap.get(username);
    }
}
