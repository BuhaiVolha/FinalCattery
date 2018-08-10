package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.impl.user.TakeAllOffersCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.entity.ReservationStatus;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.entity.Role;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TakeAllReservationsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllOffersCommand.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");
    private static final String ALL_RESERVATIONS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.reservations");


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReservationService reservationService = ServiceFactory.getInstance().getReservationService();
        List<Reservation> reservations;

        String pageValue = requestContent.getParameter("page");
        int page = (pageValue == null) ? 1 : Integer.parseInt(pageValue);
        int userId = (int) requestContent.getSessionAttribute("userId");
        int pageCount;

        if (requestContent.getSessionAttribute("role") == Role.USER) {
            reservations = reservationService
                    .takeAllReservationsForUser(userId, page, 6);
            pageCount = reservationService.getReservationsPageCountByUserId(userId, 6);

        } else {
            reservations = reservationService.takeAllReservationsByStatus(ReservationStatus.NEW, page, 6);
            pageCount = reservationService.getReservationsPageCountByStatus(ReservationStatus.NEW, 6);
        }

        requestContent.setAttribute("pageCount", pageCount);
        requestContent.setAttribute("page", page);
        requestContent.setAttribute("reservations", reservations);

        return new RequestResult(NavigationType.FORWARD, ALL_RESERVATIONS_PAGE);
    }
}
