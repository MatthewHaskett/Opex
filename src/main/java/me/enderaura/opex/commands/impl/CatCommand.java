package me.enderaura.opex.commands.impl;

import com.google.gson.JsonElement;
import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.misc.APIRequestUtils;
import me.enderaura.opex.misc.BotUtils;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.List;

/**
 * @author Enderaura
 * @since 25/12/2017 21:12.
 */
public class CatCommand extends IDiscordCommand {
    public CatCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {
        String element = APIRequestUtils.getString("http://thecatapi.com/api/images/get?format=src");

        if(element == null){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst requesting cat").withColor(Color.RED).build());
            return;
        }

        BotUtils.sendMessage(channel, element);

        System.out.println(element);


    }
}
