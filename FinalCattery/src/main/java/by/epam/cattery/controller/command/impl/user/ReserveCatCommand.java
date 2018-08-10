package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class ReserveCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ReserveCatCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReservationService reservationService = ServiceFactory.getInstance().getReservationService();

        Reservation reservation = createReservation(requestContent);
        reservationService.makeReservation(reservation);

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }


    private Reservation createReservation(RequestContent requestContent) {
        Reservation reservation = new Reservation();

        reservation.setUserMadeReservationId((int) requestContent.getSessionAttribute(SessionConst.ID));
        reservation.setCatId(Integer.parseInt(requestContent.getParameter(RequestConst.CAT_ID)));
        reservation.setPedigreeType(CatPedigreeType.valueOf(requestContent.getParameter(RequestConst.PEDIGREE_TYPE).toUpperCase()));
        reservation.setTotalCost(Double.parseDouble(requestContent.getParameter(RequestConst.TOTAL_COST)));
        reservation.setDateOfReservation(new Timestamp(System.currentTimeMillis()));

        return reservation;
    }
}
