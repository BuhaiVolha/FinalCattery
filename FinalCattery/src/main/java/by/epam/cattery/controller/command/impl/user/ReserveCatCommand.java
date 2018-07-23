package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

public class ReserveCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ReserveCatCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Reservation reservation = createReservation(request);

            ReservationService reservationService = ServiceFactory.getInstance().getReservationService();
            reservationService.makeReservation(reservation);

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to make a reservation");
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }
    }


    private Reservation createReservation(HttpServletRequest request) {
        Reservation reservation = new Reservation();
        HttpSession session = request.getSession();

        reservation.setUserMadeReservationId(Integer.parseInt(session.getAttribute("userId").toString()));
        reservation.setCatId(Integer.parseInt(request.getParameter("catId")));
        reservation.setPedigreeType(CatPedigreeType.valueOf(request.getParameter("pedigreeType").toUpperCase()));
        reservation.setTotalCost(Double.parseDouble(request.getParameter("total")));
        reservation.setDateOfReservation(new Timestamp(System.currentTimeMillis()));

        return reservation;
    }
}
