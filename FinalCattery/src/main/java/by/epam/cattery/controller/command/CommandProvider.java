package by.epam.cattery.controller.command;

import by.epam.cattery.controller.command.impl.*;
import by.epam.cattery.controller.command.impl.admin.AddUserCatCommand;
import by.epam.cattery.controller.command.impl.expert.ApproveOfferCommand;
import by.epam.cattery.controller.command.impl.expert.BargainAboutPriceCommand;
import by.epam.cattery.controller.command.impl.expert.DeclineOfferCommand;
import by.epam.cattery.controller.command.impl.user.ShowAllOffersCommand;
import by.epam.cattery.controller.command.impl.user.OfferCatCommand;
import by.epam.cattery.controller.command.impl.user.RegistrationCommand;
import by.epam.cattery.controller.command.impl.ShowOffersByStatusCommand;
import by.epam.cattery.controller.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;

public class CommandProvider {
    private static final EnumMap<Commands, ActionCommand> commands; // капс?

//    private static final CommandProvider instance = new CommandProvider(); // капс?
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
        commands.put(Commands.SHOW_ALL_CATS, new ShowAllCatsCommand());
        commands.put(Commands.APPROVED_REVIEWS, new ShowApprovedReviewsCommand());
        commands.put(Commands.OFFER_CAT, new OfferCatCommand());
        commands.put(Commands.ALL_OFFERS, new ShowAllOffersCommand());
        commands.put(Commands.OFFERS_BY_STATUS, new ShowOffersByStatusCommand());
        commands.put(Commands.DECLINE_OFFER, new DeclineOfferCommand());
        commands.put(Commands.SINGLE_OFFER, new GoToSingleOfferCommand());
        commands.put(Commands.BARGAIN, new BargainAboutPriceCommand());
        commands.put(Commands.APPROVE, new ApproveOfferCommand());
        commands.put(Commands.ADD_USER_CAT, new AddUserCatCommand());
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
