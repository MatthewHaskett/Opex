package me.enderaura.opex.leveling;

import me.enderaura.opex.misc.BotUtils;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Enderaura
 * @since 28/12/2017 21:30.
 */
public class LevelingListener {

    @EventSubscriber
    public void onMessage(MessageReceivedEvent event){
        if(!LevelingData.getInstance().getXpCooldown().containsKey(event.getAuthor()) || LevelingData.getInstance().getCooldownForUser(event.getAuthor()) <= System.currentTimeMillis()){
            LevelingData.getInstance().setCooldownForUser(event.getAuthor(), System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1));

            int current;


            if(LevelingData.getInstance().getXp().containsKey(event.getAuthor())){
                current = LevelingData.getInstance().getXpForUser(event.getAuthor());
            }else {
                current = 0;
            }

            int currentLvl = Levels.calculate(current);
            LevelingData.getInstance().setXpForUser(event.getAuthor(), current+ new Random(675489).nextInt(7) + 4);

            if(currentLvl < Levels.calculate(LevelingData.getInstance().getXpForUser(event.getAuthor()))){
                BotUtils.sendMessage(event.getChannel(), new EmbedBuilder().withTitle("Congratulations").withDesc(event.getAuthor().getName() + " leveled up :tada:!").withColor(Color.GREEN).build());
            }
        }
    }

}
