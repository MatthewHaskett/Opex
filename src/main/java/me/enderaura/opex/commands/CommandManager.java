package me.enderaura.opex.commands;

/**
 * @author Enderaura
 * @since 25/12/2017 12:11.
 */
public class CommandManager {

    public static void registerCommand(IDiscordCommand command){
        CommandHandler.commandMap.put(command.getName(), (event, args) -> command.execute(event.getChannel(), event.getAuthor(), args));
    }

    public static void registerCommands(IDiscordCommand[] commands){
        for(IDiscordCommand command : commands)
            registerCommand(command);
    }

}
