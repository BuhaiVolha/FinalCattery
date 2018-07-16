package by.epam.cattery.controller.command;

import by.epam.cattery.controller.command.impl.*;
import by.epam.cattery.controller.command.impl.admin.*;
import by.epam.cattery.controller.command.impl.expert.*;
import by.epam.cattery.controller.command.impl.user.*;
import by.epam.cattery.controller.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;

public class CommandProvider {
    // заменить на стр или чтение из файла ченить такое
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
        commands.put(Commands.DECLINE_OFFER, new DeclineOfferCommand());
        commands.put(Commands.SINGLE_OFFER, new GoToSingleOfferCommand());
        commands.put(Commands.BARGAIN, new BargainAboutPriceCommand());
        commands.put(Commands.APPROVE, new ApproveOfferCommand());
        commands.put(Commands.ADD_CAT, new AddCatCommand());
        commands.put(Commands.CABINET, new ShowCabinetCommand());
        commands.put(Commands.ALL_USERS, new ShowAllUsersCommand());
        commands.put(Commands.COLOUR_PREFERENCE, new SetColourPreferenceCommand());
        commands.put(Commands.STATISTICS, new ShowStatisticsCommand());
        commands.put(Commands.BAN, new BanCommand());
        commands.put(Commands.UNBAN, new UnbanCommand());
        commands.put(Commands.DISCOUNT, new MakeDiscountCommand());
        commands.put(Commands.MAKE_EXPERT, new MakeExpertCommand());
        commands.put(Commands.UNMAKE_EXPERT, new UnmakeExpertCommand());
        commands.put(Commands.ACCEPT_PRICE, new AcceptOfferedPriceCommand());
        commands.put(Commands.DELETE_OFFER, new DeleteOfferCommand());
        commands.put(Commands.SINGLE_CAT, new GoToSingleCatCommand());
        commands.put(Commands.APPROVED_OFFERS, new ShowApprovedOffersCommand());
        commands.put(Commands.AWAITING_OFFERS, new ShowAwaitingOffersCommand());
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
