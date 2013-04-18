package net.daboross.bukkitdev.uberchat;

/**
 *
 * @author daboross
 */
public class UberChatClassDatabase {

    private final PlayerColorz playerColorz;
    private final PlayerColorzListener playerColorzListener;
    private final RandomNumberz randomNumberz;
    private final Colorizor colorizor;

    public UberChatClassDatabase() {
        colorizor = new Colorizor();
        randomNumberz = new RandomNumberz(this);
        playerColorz = new PlayerColorz(this);
        playerColorzListener = new PlayerColorzListener(this);
    }

    public PlayerColorz getPlayerColorz() {
        return playerColorz;
    }

    public PlayerColorzListener getPlayerColorzListener() {
        return playerColorzListener;
    }

    public RandomNumberz getRandomNumberz() {
        return randomNumberz;
    }

    public Colorizor getColorizor() {
        return colorizor;
    }
}
