package me.enderaura.opex.commands;

import me.enderaura.opex.misc.BotUtils;
import me.enderaura.opex.lavaplayer.MusicUtils;
import me.enderaura.opex.lavaplayer.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CommandHandler {

    public static Map<String, Command> commandMap = new HashMap<>();

    static {
        AudioSourceManagers.registerRemoteSources(MusicUtils.playerManager);
        AudioSourceManagers.registerLocalSource(MusicUtils.playerManager);
    }



    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {

        String[] argArray = event.getMessage().getContent().split(" ");

        if(argArray.length == 0)
            return;


        if(!argArray[0].startsWith(BotUtils.BOT_PREFIX))
            return;


        String commandStr = argArray[0].substring(BotUtils.BOT_PREFIX.length());


        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0);


        if(commandMap.containsKey(commandStr))
            commandMap.get(commandStr).runCommand(event, argsList);

    }

}
