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

     //парсинг json
    static {
        commands = new EnumMap<>(Commands.class);

        commands.put(Commands.REGISTRATION, new RegistrationCommand());
        commands.put(Commands.LOGIN, new LoginCommand());
        commands.put(Commands.LOGOUT, new LogoutCommand());
        commands.put(Commands.LANGUAGE, new LanguageCommand());
        commands.put(Commands.ALL_CATS, new TakeAllCatsCommand());
        commands.put(Commands.ALL_REVIEWS, new TakeAllReviewsCommand());
        commands.put(Commands.OFFER_CAT, new OfferCatCommand());
        commands.put(Commands.ALL_OFFERS, new TakeAllOffersCommand());
        commands.put(Commands.DECLINE_OFFER, new DeclineOfferCommand());
        commands.put(Commands.SINGLE_OFFER, new GoToSingleOfferCommand());
        commands.put(Commands.BARGAIN, new BargainAboutPriceCommand());
        commands.put(Commands.APPROVE, new ApproveOfferCommand());
        commands.put(Commands.ADD_CAT, new AddCatCommand());
        commands.put(Commands.CABINET, new GoToCabinetCommand());
        commands.put(Commands.ALL_USERS, new TakeAllUsersCommand());
        commands.put(Commands.COLOUR_PREFERENCE, new SetColourPreferenceCommand());
        commands.put(Commands.STATISTICS, new CountColourPreferenceStatisticsCommand());
        commands.put(Commands.BAN, new BanUserCommand());
        commands.put(Commands.UNBAN, new UnbanUserCommand());
        commands.put(Commands.DISCOUNT, new MakeDiscountCommand());
        commands.put(Commands.MAKE_EXPERT, new MakeExpertCommand());
        commands.put(Commands.UNMAKE_EXPERT, new UnmakeExpertCommand());
        commands.put(Commands.ACCEPT_PRICE, new AcceptOfferedPriceCommand());
        commands.put(Commands.DELETE_OFFER, new DeleteOfferCommand());
        commands.put(Commands.SINGLE_CAT, new GoToSingleCatCommand());
        commands.put(Commands.APPROVED_OFFERS, new TakeApprovedOffersCommand());
        commands.put(Commands.AWAITING_OFFERS, new TakeAwaitingOffersCommand());
        commands.put(Commands.EDIT_PERSONAL_INFO, new EditPersonalInformationCommand());
        commands.put(Commands.DELETE_CAT, new DeleteCatCommand());
        commands.put(Commands.EDIT_CAT, new EditCatCommand());
        commands.put(Commands.AVAILABLE_CATS, new TakeAvailableCatsCommand());
        commands.put(Commands.RESERVE_CAT, new ReserveCatCommand());
        commands.put(Commands.ALL_RESERVATIONS, new TakeAllReservationsCommand());
        commands.put(Commands.DECLINE_EXPIRED_RESERVATIONS, new DeclineExpiredReservationsCommand());
        commands.put(Commands.CANCEL_RESERVATION, new CancelReservationCommand());
        commands.put(Commands.DELETE_RESERVATION, new DeleteReservationCommand());
        commands.put(Commands.RENEW_RESERVATION, new RenewReservationCommand());
        commands.put(Commands.SELL_CAT, new CompleteReservationCommand());
        commands.put(Commands.PEDIGREE, new CountPedigreeStatisticsCommand());
        commands.put(Commands.SEARCH, new SearchCommand());
        commands.put(Commands.WRITE_REVIEW, new AddReviewCommand());
        commands.put(Commands.DELETE_REVIEW, new DeleteReviewCommand());
        commands.put(Commands.EDIT_REVIEW, new EditReviewCommand());
        commands.put(Commands.SINGLE_REVIEW, new GoToSingleReviewCommand());
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
