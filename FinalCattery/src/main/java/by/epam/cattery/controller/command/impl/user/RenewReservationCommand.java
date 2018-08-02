package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.Reservation;
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

public class RenewReservationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RenewReservationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));

            ReservationService reservationService = ServiceFactory.getInstance().getReservationService();
            reservationService.renewReservation(reservationId);

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));

        } catch (ServiceException e) {
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
            logger.log(Level.ERROR, "Cancelling reservation failed: ", e);
        }
    }
}
