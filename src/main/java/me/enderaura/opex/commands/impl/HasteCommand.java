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
 * @since 28/12/2017 21:07.
 */
public class HasteCommand extends IDiscordCommand {
    public HasteCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {
        if(args.isEmpty()){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Enter text to upload to hastebin.").withColor(Color.RED).build());
            return;
        }

        JsonElement element = APIRequestUtils.post("https://hastebin.com/documents", String.join(" ", args));

        if(element == null){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst uploading.").withColor(Color.RED).build());
            return;
        }

        String url = "https://www.hastebin.com/" + element.getAsJsonObject().get("key").getAsString();

        BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Uploaded").withColor(Color.GREEN).withDesc(url).build());
    }
}
