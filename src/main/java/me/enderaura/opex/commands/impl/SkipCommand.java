package me.enderaura.opex.commands.impl;

import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.lavaplayer.MusicUtils;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;

/**
 * @author Enderaura
 * @since 25/12/2017 12:37.
 */
public class SkipCommand extends IDiscordCommand{
    public SkipCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {

        MusicUtils.skipTrack(channel);

    }
}
