package net.daboross.bukkitdev.uberchat.randomnumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.bukkit.ChatColor;

/**
 *
 * @author daboross
 */
public class PlayerColorStorage {

    private final Random random = new Random();

    private final static String[][] colorCombos = new String[][]{
        {ChatColor.RED.toString(), ChatColor.BLUE.toString(), ChatColor.YELLOW.toString()},
        {ChatColor.BLACK.toString(), ChatColor.GRAY.toString(), ChatColor.WHITE.toString()},
        {ChatColor.AQUA.toString(), ChatColor.RED.toString(), ChatColor.DARK_PURPLE.toString()},
        {ChatColor.GREEN.toString(), ChatColor.DARK_PURPLE.toString(), ChatColor.GOLD.toString()},
        {ChatColor.RED.toString(), ChatColor.GOLD.toString(), ChatColor.YELLOW.toString()},
        {ChatColor.DARK_PURPLE.toString(), ChatColor.LIGHT_PURPLE.toString(), ChatColor.WHITE.toString()},
        {ChatColor.DARK_GREEN.toString(), ChatColor.GREEN.toString(), ChatColor.WHITE.toString()}};
    private final Map<String, String[]> playerColorCombos;
    private final Map<String, String> playerSymbols;

    public PlayerColorStorage() {
        this.playerSymbols = new HashMap<String, String>();
        this.playerColorCombos = new HashMap<String, String[]>();
    }

    private String[] randomColorCombo() {
        int i = random.nextInt(colorCombos.length);
        return colorCombos[i];
    }

    public void newSet(String username) {
        playerColorCombos.put(username, randomColorCombo());
        playerSymbols.put(username, String.valueOf(random.nextInt(10)));
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
        return currentColorCombos[random.nextInt(currentColorCombos.length)] + playerSymbols.get(username);
    }
}
