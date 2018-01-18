package me.enderaura.opex.leveling;

/**
 * @author Enderaura
 * @since 18/01/2018 17:40.
 */
public class Levels {

    public static int calculate(int xp){
        return (xp - (xp%300)) / 300;
    }

}
