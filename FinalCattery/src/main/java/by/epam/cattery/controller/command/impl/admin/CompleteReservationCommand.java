package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;

import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.entity.Role;
import by.epam.cattery.util.ConfigurationManager;

import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command for selling cat.
 *
 */
public class CompleteReservationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(CompleteReservationCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);
    private static final String ACCESS_DENIED_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.ACCESS_DENIED_PAGE);


    /**
     * If user's role isn't {@code ADMIN} redirects to the access denied page with message shown.
     * Otherwise setting the reservation {@code DONE} and its cat - {@code SOLD} and redirects to the success page.
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String path = ACCESS_DENIED_PAGE;

        if (requestContent.getSessionAttribute(SessionConst.ROLE) == Role.ADMIN) {
            ReservationService reservationService = ServiceFactory.getInstance().getReservationService();

            int reservationId = Integer.parseInt(requestContent.getParameter(RequestConst.RESERVATION_ID));
            reservationService.sellCat(reservationId);
            path = SUCCESS_PAGE;
        }

        return new RequestResult(NavigationType.REDIRECT, path);
    }
}
