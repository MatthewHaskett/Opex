package me.enderaura.opex;

import me.enderaura.opex.commands.CommandHandler;
import me.enderaura.opex.commands.CommandManager;
import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.commands.impl.*;
import me.enderaura.opex.leveling.LevelingListener;
import me.enderaura.opex.misc.BotUtils;
import me.enderaura.opex.misc.LoginHandler;
import sx.blah.discord.api.IDiscordClient;

public class MainRunner {

    private IDiscordClient discordClient;

    public static void main(String[] args){
        new MainRunner().main();
    }

    private static MainRunner instance;

    private MainRunner(){
        instance = this;
    }

    public static MainRunner getInstance() {
        return instance;
    }

    private void main(){
        discordClient = BotUtils.getBuiltDiscordClient("TOKEN"); // Note to self: REGENERATE LEAKED TOKEN, AND HAVE IT PULLED FROM AN IGNORED FILE!!!!
        discordClient.getDispatcher().registerListener(new CommandHandler());
        discordClient.getDispatcher().registerListener(new LoginHandler());
        discordClient.getDispatcher().registerListener(new LevelingListener());
        discordClient.login();

        CommandManager.registerCommands(new IDiscordCommand[]{
                new JoinCommand("join"),
                new LeaveCommand("leave"),
                new PlayCommand("play"),
                new SkipCommand("skip"),
                new YoutubeCommand("youtube"),
                new JokeCommand("joke"),
                new CatCommand("cat"),
                new DogCommand("dog"),
                new GifCommand("gif"),
                new McCommand("mc"),
                new HasteCommand("haste"),
                new RankCommand("rank")
        });
    }

    public IDiscordClient getDiscordClient() {
        return discordClient;
    }
}
