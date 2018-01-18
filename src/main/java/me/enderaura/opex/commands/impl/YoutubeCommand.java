package me.enderaura.opex.commands.impl;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.misc.BotUtils;
import me.enderaura.opex.misc.YoutubeSearch;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author Enderaura
 * @since 25/12/2017 12:45.
 */
public class YoutubeCommand extends IDiscordCommand {

    public static final RegularExpression URL_REGEX = new RegularExpression("^(https?\\:\\/\\/)?(www\\.)?(youtube\\.com|youtu\\.?be)\\/.+$");

    public YoutubeCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {
        if(args.isEmpty()){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Please specify a search query.").withColor(Color.RED).build());
            return;
        }

        String query = String.join(" ", args);


        Iterator<SearchResult> resultIterator = YoutubeSearch.search(query);

        if(resultIterator==null){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst searching for: " + query).withColor(Color.RED).build());
            return;
        }

        if(!resultIterator.hasNext()){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("No results found for: " + query).withColor(Color.RED).build());
            return;
        }

        SearchResult result = resultIterator.next();
        ResourceId resourceId = result.getId();

        if(!resourceId.getKind().equals("youtube#video")){
            BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Error").withDesc("Error whilst searching for: " + query).withColor(Color.RED).build());
            return;
        }

        EmbedBuilder builder = new EmbedBuilder()
                .withColor(Color.BLUE)
                .withTitle("Result")
                .withDesc(query)
                .appendField("Title", result.getSnippet().getTitle(), true)
                .appendField("URL", "https://youtube.com/watch?v=" + resourceId.getVideoId(), false)
                .withThumbnail(result.getSnippet().getThumbnails().getDefault().getUrl());

        BotUtils.sendMessage(channel, builder.build());
    }
}
