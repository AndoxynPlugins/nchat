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
