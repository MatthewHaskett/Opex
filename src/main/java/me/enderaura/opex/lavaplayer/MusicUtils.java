package me.enderaura.opex.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.enderaura.opex.misc.BotUtils;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Enderaura
 * @since 25/12/2017 12:04.
 */
public class MusicUtils {

    public static final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();;;
    public static final Map<Long, GuildMusicManager> musicManagers  = new HashMap<>();;


    public static synchronized GuildMusicManager getGuildAudioPlayer(IGuild guild) {
        long guildId = Long.parseLong(guild.getStringID());
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setAudioProvider(musicManager.getAudioProvider());

        return musicManager;
    }

    public static void loadAndPlay(final IChannel channel, final String trackUrl) {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());

        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                BotUtils.sendMessage(channel,
                        new EmbedBuilder().withColor(Color.GREEN).withTitle("Music").withDesc("Adding to queue " + track.getInfo().title).build());

                play(musicManager, track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                BotUtils.sendMessage(channel,
                        new EmbedBuilder().withColor(Color.GREEN).withTitle("Music").withDesc("Adding to queue " + firstTrack.getInfo().title + " (first track of playlist " + playlist.getName() + ")").build());

                play(musicManager, firstTrack);
            }

            @Override
            public void noMatches() {
                BotUtils.sendMessage(channel, "Nothing found by " + trackUrl);
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                BotUtils.sendMessage(channel, "Could not play: " + exception.getMessage());
            }
        });
    }

    public static void play(GuildMusicManager musicManager, AudioTrack track) {

        musicManager.getScheduler().queue(track);
    }

    public static void skipTrack(IChannel channel) {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        musicManager.getScheduler().nextTrack();

        BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Skipped").withDesc("Skipped to the next track!").withColor(Color.GREEN).build());
    }

}
