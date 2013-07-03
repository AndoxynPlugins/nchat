/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author daboross
 */
public class PlayerInfoTracker {

    private static final Set<String> colormeEnabled = new HashSet<String>();
    private static final Map<String, String> replytoMap = new HashMap<String, String>();

    public static void setColormeEnabled(String username, boolean enabled) {
        if (enabled) {
            colormeEnabled.add(username);
        } else {
            colormeEnabled.remove(username);
        }
    }

    public static boolean getColormeEnabled(String username) {
        return colormeEnabled.contains(username);
    }

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
