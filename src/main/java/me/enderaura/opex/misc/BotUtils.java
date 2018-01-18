package me.enderaura.opex.misc;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

public class BotUtils {

    public static String BOT_PREFIX = "--";

    public static IDiscordClient getBuiltDiscordClient(String token){

        return new ClientBuilder()
                .withToken(token)
                .withRecommendedShardCount()
                .build();

    }

    public static void sendMessage(IChannel channel, String message){


        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });

    }

    public static void sendMessage(IChannel channel, EmbedObject message){


        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Embed could not be sent with error: ");
                e.printStackTrace();
            }
        });

    }
}
