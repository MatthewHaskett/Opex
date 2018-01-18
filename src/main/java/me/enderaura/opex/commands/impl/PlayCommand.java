package me.enderaura.opex.commands.impl;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.lavaplayer.MusicUtils;
import me.enderaura.opex.misc.BotUtils;
import me.enderaura.opex.misc.YoutubeSearch;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author Enderaura
 * @since 25/12/2017 12:35.
 */
public class PlayCommand extends IDiscordCommand {
    public PlayCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {
        IVoiceChannel botVoiceChannel = sender.getVoiceStateForGuild(channel.getGuild()).getChannel();

        if(botVoiceChannel == null) {
            BotUtils.sendMessage(channel, "Not in a voice channel, join one and then use join");
            return;
        }

        String searchStr = String.join(" ", args);

        if(YoutubeCommand.URL_REGEX.matches(searchStr))
            MusicUtils.loadAndPlay(channel, searchStr);

        else{
            Iterator<SearchResult> resultIterator = YoutubeSearch.search(searchStr);

            if(resultIterator==null){
                BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst searching for: " + searchStr).withColor(Color.RED).build());
                return;
            }

            if(!resultIterator.hasNext()){
                BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("No results found for: " + searchStr).withColor(Color.RED).build());
                return;
            }

            SearchResult result = resultIterator.next();
            ResourceId resourceId = result.getId();

            if(!resourceId.getKind().equals("youtube#video")){
                BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst searching for: " + searchStr).withColor(Color.RED).build());
                return;
            }

            MusicUtils.loadAndPlay(channel, "https://youtube.com/watch?v=" + resourceId.getVideoId());
        }
    }
}
