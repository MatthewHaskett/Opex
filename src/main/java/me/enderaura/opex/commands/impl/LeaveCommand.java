package me.enderaura.opex.commands.impl;

import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.lavaplayer.MusicUtils;
import me.enderaura.opex.lavaplayer.TrackScheduler;
import me.enderaura.opex.misc.BotUtils;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.List;

/**
 * @author Enderaura
 * @since 25/12/2017 12:33.
 */
public class LeaveCommand extends IDiscordCommand {

    public LeaveCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {
        IVoiceChannel botVoiceChannel = sender.getVoiceStateForGuild(channel.getGuild()).getChannel();

        if(botVoiceChannel == null) {
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("I am not in a voice channel").withColor(Color.RED).build());
            return;
        }

        TrackScheduler scheduler = MusicUtils.getGuildAudioPlayer(channel.getGuild()).getScheduler();
        scheduler.getQueue().clear();
        scheduler.nextTrack();

        BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Success").withDesc("Left the channel").withColor(Color.GREEN).build());

        botVoiceChannel.leave();
    }
}
