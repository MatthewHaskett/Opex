package me.enderaura.opex.leveling;

import sx.blah.discord.handle.obj.IUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Enderaura
 * @since 28/12/2017 21:31.
 */
public class LevelingData {
    private static LevelingData ourInstance = new LevelingData();

    public static LevelingData getInstance() {
        return ourInstance;
    }

    private LevelingData() {
    }

    private Map<IUser, Integer> xp = new HashMap<>();
    private Map<IUser, Long> xpCooldown = new HashMap<>();

    public Map<IUser, Long> getXpCooldown() {
        return xpCooldown;
    }

    public Map<IUser, Integer> getXp() {
        return xp;
    }

    public int getXpForUser(IUser user){
        return xp.get(user);
    }

    public void setXpForUser(IUser user, int xp){
        this.xp.put(user, xp);
    }

    public void setCooldownForUser(IUser user, long time){
        xpCooldown.put(user, time);
    }

    public long getCooldownForUser(IUser user){
        return xpCooldown.get(user);
    }
}
