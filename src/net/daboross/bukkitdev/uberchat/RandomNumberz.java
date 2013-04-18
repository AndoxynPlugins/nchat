package net.daboross.bukkitdev.uberchat;

import java.util.Random;

/**
 *
 * @author daboross
 */
public class RandomNumberz {

    private final UberChatClassDatabase database;

    public RandomNumberz(UberChatClassDatabase database) {
        this.database = database;
    }
    private char[] numberz = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'f', 'e', 'g', 'h', 'n', 'b', 'c', 'v', 'x', 'z', 'u', 'o', '=', '+'};
    private Random r = new Random();

    public String randomNumber() {
        int i = r.nextInt(numberz.length);
        return database.getColorizor().randomColor() + numberz[i];
    }
}
