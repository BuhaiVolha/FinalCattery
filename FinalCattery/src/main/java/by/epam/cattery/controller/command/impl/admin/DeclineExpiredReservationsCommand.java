package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeclineExpiredReservationsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeclineExpiredReservationsCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReservationService reservationService = ServiceFactory.getInstance().getReservationService();

        reservationService.declineExpiredReservations();

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }
}
