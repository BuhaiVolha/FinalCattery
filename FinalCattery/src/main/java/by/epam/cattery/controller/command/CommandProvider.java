package by.epam.cattery.controller.command;

import by.epam.cattery.controller.command.exception.CommandParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private static final CommandProvider instance = new CommandProvider();
    private static Map<String, ActionCommand> commands;


    static {
        try {
            commands = new CommandParser().getCommands();

        } catch (CommandParserException e) {
            logger.log(Level.ERROR,"Failed to read commands from file", e);
            throw new RuntimeException(e);
        }
    }


    public ActionCommand defineCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        logger.log(Level.DEBUG, "Command name is " + commandName);

        ActionCommand command = commands.get(commandName);

        if (command == null) {
            logger.log(Level.WARN, "Command was null");
        }
        return command;

    }


    public static CommandProvider getInstance() {
        return instance;
    }
}
