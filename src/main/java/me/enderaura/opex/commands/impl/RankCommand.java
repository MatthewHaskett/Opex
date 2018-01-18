package me.enderaura.opex.commands.impl;

import me.enderaura.opex.commands.IDiscordCommand;
import me.enderaura.opex.leveling.LevelingData;
import me.enderaura.opex.leveling.Levels;
import me.enderaura.opex.misc.BotUtils;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.List;

/**
 * @author Enderaura
 * @since 28/12/2017 21:45.
 */
public class RankCommand extends IDiscordCommand{

    public RankCommand(String name) {
        super(name);
    }

    @Override
    public void execute(IChannel channel, IUser sender, List<String> args) {

        int xp = 0;

        if(LevelingData.getInstance().getXp().containsKey(sender)) xp = LevelingData.getInstance().getXpForUser(sender);

        BotUtils.sendMessage(channel, new EmbedBuilder().withTitle("Rank").withColor(Color.ORANGE).withDesc("Leveling data for " + sender.getName()).appendField("XP", xp + "", false).appendField("Level", "" + Levels.calculate(xp), false).build());
    }
}
