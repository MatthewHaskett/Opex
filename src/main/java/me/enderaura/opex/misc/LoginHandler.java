package me.enderaura.opex.misc;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

import java.util.Timer;

/**
 * @author Enderaura
 * @since 23/12/2017 14:09.
 */
public class LoginHandler {

    @EventSubscriber
    public void onLogin(ReadyEvent event){
        event.getClient().changeUsername("Opex");

        Timer timer = new Timer();
        timer.schedule(new StatusHandler(), 0, 10000);
    }

}
