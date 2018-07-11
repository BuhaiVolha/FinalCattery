package by.epam.cattery.controller.command;

import by.epam.cattery.controller.command.impl.*;
import by.epam.cattery.controller.util.MessageManager;

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
        commands.put(Commands.APPROVED_REVIEWS, new ShowApprovedReviewsCommand());
        commands.put(Commands.OFFER_KITTEN, new OfferKittenCommand());
        commands.put(Commands.ALL_OFFERS, new ShowAllOffersCommand());
        commands.put(Commands.OFFERS_BY_STATUS, new ShowOffersByStatusCommand());
        commands.put(Commands.DECLINE_OFFER, new DeclineOfferCommand());
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
