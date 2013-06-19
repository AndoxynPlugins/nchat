/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bukkitdev.uberchat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.ChatColor;

/**
 *
 * @author daboross
 */
public class SwearChecker {

    private static final Set<String> swearWords = new HashSet<String>();
    private static final Map<String, Boolean> isWordMap = new HashMap<String, Boolean>();

    static {
        swearWords.add("fuck");
        isWordMap.put("fuck", false);
        swearWords.add("nigger");
        isWordMap.put("nigger", false);
        swearWords.add("bitch");
        isWordMap.put("bitch", false);
        swearWords.add("shit");
        isWordMap.put("shit", false);
        swearWords.add("ass");
        isWordMap.put("ass", true);
        swearWords.add("crap");
        isWordMap.put("crap", false);
        swearWords.add("fag");
        isWordMap.put("fag", false);
        swearWords.add("dick");
        isWordMap.put("dick", false);
        swearWords.add("cunt");
        isWordMap.put("cunt", false);
    }
    private static char[] dividerChars = {' ', ',', '.'};

    public static String swearCheck(String input) {
        String msg = input;
        boolean msgNonColor = false;
        for (String rawSwear : swearWords) {
            boolean swearIsWord = isWordMap.get(rawSwear);
            String replacementWord = getReplacementWord(rawSwear.length());
            if (swearIsWord) {
                for (char divider : dividerChars) {
                    String swearIsWordReplacement = (divider + replacementWord + divider);
                    String swearRegex = "(?i)" + (swearIsWord ? (divider + rawSwear + divider) : rawSwear);
                    msg = msg.replaceAll(swearRegex, swearIsWordReplacement);
                }
            } else {
                String swearRegex = "(?i)" + (rawSwear);
                msg = msg.replaceAll(swearRegex, replacementWord);
            }
            if (swearIsWord) {
                if (msg.equals(rawSwear)) {
                    msg = replacementWord;
                } else {
                    for (char divider : dividerChars) {
                        if (msg.toLowerCase().endsWith(divider + rawSwear.toLowerCase())) {
                            msg = msg.substring(0, msg.length() - rawSwear.length()).concat(replacementWord);
                        }
                        if (msg.toLowerCase().startsWith(rawSwear.toLowerCase() + divider)) {
                            msg = replacementWord.concat(msg.substring(rawSwear.length(), msg.length()));
                        }
                    }
                }
            }
            if (!msgNonColor) {
                String noColorOrig = ChatColor.stripColor(msg);
                String noColorMsg = noColorOrig;
                if (noColorMsg.equals(msg)) {
                    msgNonColor = true;
                } else {
                    if (swearIsWord) {
                        for (char divider : dividerChars) {
                            String swearIsWordReplacement = (divider + replacementWord + divider);
                            String swearRegex = "(?i)" + (swearIsWord ? (divider + rawSwear + divider) : rawSwear);
                            noColorMsg = noColorMsg.replaceAll(swearRegex, swearIsWordReplacement);
                        }
                    } else {
                        String swearRegex = "(?i)" + (rawSwear);
                        noColorMsg = noColorMsg.replaceAll(swearRegex, replacementWord);
                    }
                    if (swearIsWord) {
                        if (noColorMsg.equals(rawSwear)) {
                            noColorMsg = replacementWord;
                        } else {
                            for (char divider : dividerChars) {
                                if (noColorMsg.toLowerCase().endsWith(divider + rawSwear.toLowerCase())) {
                                    noColorMsg = noColorMsg.substring(0, noColorMsg.length() - rawSwear.length()).concat(replacementWord);
                                }
                                if (noColorMsg.toLowerCase().startsWith(rawSwear.toLowerCase() + divider)) {
                                    noColorMsg = replacementWord.concat(noColorMsg.substring(rawSwear.length(), noColorMsg.length()));
                                }
                            }
                        }
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
