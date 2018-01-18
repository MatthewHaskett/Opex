package me.enderaura.opex.commands.impl;

import com.google.gson.JsonParser;
import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.misc.BotUtils;
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
 * @since 26/12/2017 21:29.
 */
public class McCommand extends IDiscordCommand {
    public McCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {
        if(args.size() != 2){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Usage: --mc [uuid|pastnames] [username]").withColor(Color.RED).build());
            return;
        }

        if(args.get(0).equalsIgnoreCase("uuid")){


            try {

                URL obj = new URL("https://api.mojang.com/users/profiles/minecraft/" + args.get(1));
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Opex/Discord Bot");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                con.setRequestProperty("Accept", "application/json");

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) response.append(inputLine);
                in.close();

                BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("UUID for " + args.get(1)).withDesc(new JsonParser().parse(response.toString()).getAsJsonObject().get("id").getAsString()).build());


            } catch (IOException | IllegalStateException e) {
                BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst searching for: " + args.get(1)).withColor(Color.RED).build());
                return;
            }

        }else if(args.get(0).equalsIgnoreCase("pastnames")){

        }else {
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Usage: --mc [uuid|pastnames] [username]").withColor(Color.RED).build());
        }
    }
}
