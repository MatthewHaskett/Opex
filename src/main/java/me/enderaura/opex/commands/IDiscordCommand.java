package me.enderaura.opex.commands;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;

/**
 * @author Enderaura
 * @since 25/12/2017 12:11.
 */
public abstract class IDiscordCommand {

    private String name;

    public abstract void execute(IChannel channel, IUser sender, List<String> args);

    public IDiscordCommand(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
