package net.daboross.bukkitdev.uberchat.randomnumbers;

import java.util.Random;
import net.daboross.bukkitdev.uberchat.Colorizor;

/**
 *
 * @author daboross
 */
public class RandomNumberGenerator {

    private final char[] differentNumbers = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'f', 'e', 'g', 'h', 'n', 'b', 'c', 'v', 'x', 'z', 'u', 'o', '=', '+'};
    private final Random random = new Random();

    public String randomNumber() {
        int i = random.nextInt(differentNumbers.length);
        return Colorizor.randomColor() + differentNumbers[i];
    }
}
