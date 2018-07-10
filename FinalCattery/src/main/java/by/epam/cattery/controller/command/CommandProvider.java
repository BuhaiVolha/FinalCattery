package by.epam.cattery.controller.command;

import by.epam.cattery.controller.command.impl.*;
import by.epam.cattery.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;

public class CommandProvider {
    private static final EnumMap<Commands, ActionCommand> commands; // капс?
    private static final CommandProvider instance = new CommandProvider(); // капс?

//    CommandProvider() { // package?
//        commands.put(Commands.REGISTRATION, new RegistrationCommand());
//        commands.put(Commands.LOGIN, new LoginCommand());
//        commands.put(Commands.LOGOUT, new LogoutCommand());
//        commands.put(Commands.LANGUAGE, new LanguageCommand());
//    }
//
//
//    private static CommandProvider getInstance() {
//        return instance;
//    }

    static {
        commands = new EnumMap<>(Commands.class);
        commands.put(Commands.REGISTRATION, new RegistrationCommand());
        commands.put(Commands.LOGIN, new LoginCommand());
        commands.put(Commands.LOGOUT, new LogoutCommand());
        commands.put(Commands.LANGUAGE, new LanguageCommand());
        commands.put(Commands.CATS, new CatCommand());  //дурацкое название
    }


    public static ActionCommand defineCommand(HttpServletRequest request) {
        String command = request.getParameter("command");
        ActionCommand current = null; //default ???

        try {
            current = commands.get(Commands.valueOf(command.toUpperCase()));

        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", command
                    + MessageManager.getProperty("message.wrongaction"));
            //логгер
        }

        return current;
    }
}
