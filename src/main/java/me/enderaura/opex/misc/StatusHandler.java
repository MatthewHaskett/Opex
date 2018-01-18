package me.enderaura.opex.misc;

import me.enderaura.opex.MainRunner;

import java.util.TimerTask;

/**
 * @author Enderaura
 * @since 23/12/2017 14:13.
 */
public class StatusHandler extends TimerTask{

    boolean dnd = false;

    @Override
    public void run() {
        if(dnd){
            MainRunner.getInstance().getDiscordClient().online();
            MainRunner.getInstance().getDiscordClient().changePlayingText(MainRunner.getInstance().getDiscordClient().getGuilds().size() + " guilds | --help");
        }else{
            MainRunner.getInstance().getDiscordClient().dnd();
            MainRunner.getInstance().getDiscordClient().changePlayingText(MainRunner.getInstance().getDiscordClient().getUsers().size() + " users | --help");
        }

        dnd = !dnd;
    }
}
