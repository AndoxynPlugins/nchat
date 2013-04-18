package net.daboross.bukkitdev.uberchat;

import java.util.Random;

/**
 *
 * @author daboross
 */
public class RandomNumberz {

    private static Random r = new Random();

    public static String randomNumber() {
        int i = r.nextInt(10);
        return Colorizor.randomColor() + i;
    }
}
