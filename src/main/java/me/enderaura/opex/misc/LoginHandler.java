package me.enderaura.opex.misc;

import me.enderaura.opex.leveling.LevelingData;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

import java.util.Random;
import java.util.Timer;

/**
 * @author Enderaura
 * @since 23/12/2017 14:09.
 */
public class LoginHandler {

    @EventSubscriber
    public void onLogin(ReadyEvent event){
        event.getClient().changeUsername("Opex");


        LevelingData.getInstance().setXpForUser(event.getClient().getUserByID(178806549710503936L), 99);


        Timer timer = new Timer();
        timer.schedule(new StatusHandler(), 0, 10000);
    }

}
