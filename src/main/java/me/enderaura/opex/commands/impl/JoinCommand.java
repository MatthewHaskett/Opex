package me.enderaura.opex.commands.impl;

import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.misc.BotUtils;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.Color;
import java.util.List;

/**
 * @author Enderaura
 * @since 25/12/2017 12:15.
 */
public class JoinCommand extends IDiscordCommand {

    public JoinCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {
        IVoiceChannel userVoiceChannel = sender.getVoiceStateForGuild(channel.getGuild()).getChannel();

        if(userVoiceChannel == null) {
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("You are not in a voice channel").withColor(Color.RED).build());
            return;
        }

        userVoiceChannel.join();

        BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Success").withDesc("Joined your channel").withColor(Color.GREEN).build());

    }
}
