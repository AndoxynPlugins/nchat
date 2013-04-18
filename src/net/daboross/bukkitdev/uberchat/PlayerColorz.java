package net.daboross.bukkitdev.uberchat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.bukkit.ChatColor;

/**
 *
 * @author daboross
 */
public class PlayerColorz {

    private final UberChatClassDatabase database;
    private final Random r = new Random();

    public PlayerColorz(UberChatClassDatabase database) {
        this.database = database;
    }
    private final String[][] colorCombos = new String[][]{
        {ChatColor.RED.toString(), ChatColor.BLUE.toString(), ChatColor.YELLOW.toString()},
        {ChatColor.BLACK.toString(), ChatColor.GRAY.toString(), ChatColor.WHITE.toString()},
        {ChatColor.AQUA.toString(), ChatColor.RED.toString(), ChatColor.DARK_PURPLE.toString()},
        {ChatColor.GREEN.toString(), ChatColor.DARK_PURPLE.toString(), ChatColor.GOLD.toString()},
        {ChatColor.RED.toString(), ChatColor.GOLD.toString(), ChatColor.YELLOW.toString()},
        {ChatColor.DARK_PURPLE.toString(), ChatColor.LIGHT_PURPLE.toString(), ChatColor.WHITE.toString()},
        {ChatColor.DARK_GREEN.toString(), ChatColor.GREEN.toString(), ChatColor.WHITE.toString()}};
    private final Map<String, String[]> playerColorCombos = new HashMap<String, String[]>();
    private final Map<String, String> playerSymbols = new HashMap<String, String>();

    private String[] randomColorCombo() {
        int i = r.nextInt(colorCombos.length);
        return colorCombos[i];
    }

    public void newSet(String username) {
        playerColorCombos.put(username, randomColorCombo());
        playerSymbols.put(username, String.valueOf(r.nextInt(10)));
    }

    public void removeSet(String username) {
        playerColorCombos.remove(username);
        playerSymbols.remove(username);
    }

    public String getSymbol(String username) {
        String[] currentColorCombos = playerColorCombos.get(username);
        if (currentColorCombos == null) {
            newSet(username);
            currentColorCombos = playerColorCombos.get(username);
        }
        return currentColorCombos[r.nextInt(currentColorCombos.length)] + playerSymbols.get(username);
    }
}
