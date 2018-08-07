package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.impl.user.TakeAllOffersCommand;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.entity.Role;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TakeAllReservationsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllOffersCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Reservation> reservations = null;
        HttpSession session = request.getSession();

        try {
            ReservationService reservationService = ServiceFactory.getInstance().getReservationService();

            if (session.getAttribute("role") == Role.USER) {
                reservations = reservationService
                        .takeAllReservationsForUser(Integer.parseInt(session.getAttribute("userId").toString()));
            } else {
                reservations = reservationService.takeAllReservations();
            }

            request.setAttribute("reservations", reservations);

            request.getRequestDispatcher(ConfigurationManager.getInstance()
                    .getProperty("path.page.reservations")).forward(request, response);

        } catch (ServiceException e) {
            //redirect
            logger.log(Level.ERROR, "Can't show reservations: ", e);
        }
    }
}
