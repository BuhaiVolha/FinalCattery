package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.command.impl.user.TakeAllOffersCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.entity.LocaleLang;
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

    private static final String ALL_RESERVATIONS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.RESERVATIONS);
    private static final int ITEMS_PER_PAGE = 6;
    private static final int DEFAULT_PAGE = 1;


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReservationService reservationService = ServiceFactory.getInstance().getReservationService();
        List<Reservation> reservations;

        String pageValue = requestContent.getParameter(RequestConst.PAGINATION_PAGE);
        int page = (pageValue == null) ? DEFAULT_PAGE : Integer.parseInt(pageValue);
        int userId = (int) requestContent.getSessionAttribute(SessionConst.ID);
        int pageCount;
        LocaleLang localeLang = LocaleLang.valueOf(requestContent.getSessionAttribute(SessionConst.LOCALE).toString().toUpperCase());

        if (requestContent.getSessionAttribute(SessionConst.ROLE) == Role.USER) {
            reservations = reservationService
                    .takeAllReservationsForUser(userId, localeLang, page, ITEMS_PER_PAGE);
            pageCount = reservationService.getReservationsPageCountByUserId(userId, ITEMS_PER_PAGE);

        } else {
            reservations = reservationService.takeAllReservationsByStatus(ReservationStatus.NEW, localeLang, page, ITEMS_PER_PAGE);
            pageCount = reservationService.getReservationsPageCountByStatus(ReservationStatus.NEW, ITEMS_PER_PAGE);
        }

        requestContent.setPaginationParameters(pageCount, page);
        requestContent.setAttribute(RequestConst.RESERVATIONS_LIST, reservations);

        return new RequestResult(NavigationType.FORWARD, ALL_RESERVATIONS_PAGE);
    }
}
