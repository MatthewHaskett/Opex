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
 * @since 26/12/2017 20:37.
 */
public class DogCommand extends IDiscordCommand {
    public DogCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {
        JsonElement element = APIRequestUtils.get("https://dog.ceo/api/breeds/image/random");

        if(element == null){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst requesting dog").withColor(Color.RED).build());
            return;
        }

        if(!element.isJsonObject()){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst requesting dog").withColor(Color.RED).build());
            return;
        }

        String dog = element.getAsJsonObject().get("message").getAsString();

        BotUtils.sendMessage(channel, new EmbedBuilder().withColor(Color.ORANGE).withImage(dog).withTitle("Dog").withDesc("Here is your random dog!").build());
    }
}
