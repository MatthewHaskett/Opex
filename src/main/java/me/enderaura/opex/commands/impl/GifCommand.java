package me.enderaura.opex.commands.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.misc.BotUtils;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author Enderaura
 * @since 26/12/2017 20:53.
 */
public class GifCommand extends IDiscordCommand{


    public GifCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {

        if(args.isEmpty()){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Please specify a search query.").withColor(Color.RED).build());
            return;
        }

        String query = String.join(" ", args);

        try {

            URL obj = new URL("https://api.giphy.com/v1/gifs/search?api_key=viFlWydGDZuVYkeCYt3Xq50vajVzNYjQ&q=" + query + "&limit=1");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) response.append(inputLine);
            in.close();

            JsonElement element = new JsonParser().parse(response.toString());

            if(!element.isJsonObject()){
                BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst requesting gif. [1]").withColor(Color.RED).build());
                return;
            }

            element = element.getAsJsonObject().get("data");

            if(!element.isJsonArray()){
                BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst requesting gif. [2]").withColor(Color.RED).build());
                return;
            }

            element = element.getAsJsonArray().get(0);

            if(!element.isJsonObject()){
                BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst requesting gif. [3]").withColor(Color.RED).build());
                return;
            }

            String result = element.getAsJsonObject().get("embed_url").getAsString();

            BotUtils.sendMessage(channel, result);

        } catch (IOException e) {
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst requesting gif.").withColor(Color.RED).build());
            return;
        }

    }
}
