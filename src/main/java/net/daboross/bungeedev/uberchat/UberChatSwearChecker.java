/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bungeedev.uberchat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.md_5.bungee.api.ChatColor;

/**
 *
 * @author daboross
 */
public class UberChatSwearChecker {

    private static final Set<String> anywhereSwearWords = new HashSet<String>();
    private static final Set<String> wordOnlySwearWords = new HashSet<String>();
    private static final String DIVIDER_REGEX = "[^a-zA-Z]";
    private static final String REGEX_START = "^";
    private static final String REGEX_END = "$";

    static {
        anywhereSwearWords.add("fuck");
        anywhereSwearWords.add("nigger");
        anywhereSwearWords.add("bitch");
        anywhereSwearWords.add("shit");
        anywhereSwearWords.add("crap");
        anywhereSwearWords.add("fag");
        anywhereSwearWords.add("dick");
        anywhereSwearWords.add("cunt");
        anywhereSwearWords.add("cock");
        wordOnlySwearWords.add("ass");
    }

    public static String swearCheck(final String input) {
        String msg = input;
        boolean msgNonColor = false;
        for (String rawSwear : anywhereSwearWords) {
            String replacement = getReplacementWord(rawSwear.length());
            String swearRegex = "(?i)" + rawSwear;
            msg = msg.replaceAll(swearRegex, replacement);
            if (!msgNonColor) {
                final String noColorOrig = ChatColor.stripColor(msg);
                String noColorMsg = noColorOrig;
                if (noColorMsg.equals(msg)) {
                    msgNonColor = true;
                } else {
                    noColorMsg = noColorMsg.replaceAll(swearRegex, replacement);
                    if (!noColorOrig.equals(noColorMsg)) {
                        msg = noColorMsg;
                        msgNonColor = true;
                    }
                }
            }
        }
        for (String rawSwear : wordOnlySwearWords) {
            String replacement = " " + getReplacementWord(rawSwear.length()) + " ";
            String swearRegex = "(?i)" + DIVIDER_REGEX + rawSwear + DIVIDER_REGEX;
            msg = msg.replaceAll(swearRegex, replacement);
            if (msg.toLowerCase().contains(REGEX_START + rawSwear.toLowerCase() + DIVIDER_REGEX)) {
                msg = replacement + msg.substring(rawSwear.length(), msg.length());
            }
            if (msg.toLowerCase().contains(DIVIDER_REGEX + rawSwear.toLowerCase() + REGEX_END)) {
                msg = msg.substring(0, msg.length() - rawSwear.length()) + replacement;
            }
            if (msg.trim().equals(rawSwear)) {
                msg = replacement;
            }
            if (!msgNonColor) {
                final String noColorOrig = ChatColor.stripColor(msg);
                String noColorMsg = noColorOrig;
                if (noColorMsg.equals(msg)) {
                    msgNonColor = true;
                } else {
                    noColorMsg = noColorMsg.replaceAll(swearRegex, replacement);
                    if (noColorMsg.toLowerCase().contains(DIVIDER_REGEX + rawSwear.toLowerCase() + REGEX_END)) {
                        noColorMsg = noColorMsg.substring(0, noColorMsg.length() - rawSwear.length()).concat(replacement);
                    }
                    if (noColorMsg.toLowerCase().startsWith(REGEX_START + rawSwear.toLowerCase() + DIVIDER_REGEX)) {
                        noColorMsg = replacement.concat(noColorMsg.substring(rawSwear.length(), noColorMsg.length()));
                    }
                    if (noColorMsg.trim().equals(rawSwear)) {
                        noColorMsg = replacement;
                    }
                    if (!noColorOrig.equals(noColorMsg)) {
                        msg = noColorMsg;
                        msgNonColor = true;
                    }
                }
            }
        }
        return msg;
    }

    private static String getReplacementWord(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, '*');
        return String.valueOf(chars);
    }
}
