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
 * @since 25/12/2017 20:47.
 */
public class JokeCommand extends IDiscordCommand {
    public JokeCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {
        JsonElement element = APIRequestUtils.get("https://icanhazdadjoke.com/");

        if(element == null){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst requesting joke").withColor(Color.RED).build());
            return;
        }

        if(!element.isJsonObject()){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst requesting joke").withColor(Color.RED).build());
            return;
        }

        String joke = element.getAsJsonObject().get("joke").getAsString();

        BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Joke").withColor(Color.ORANGE).withDesc(joke).build());

    }
}
