package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteReservationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteReservationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            System.out.println(request.getParameter("reservationId") + " !!!");
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));

            ReservationService reservationService = ServiceFactory.getInstance().getReservationService();
            reservationService.cancelReservation(reservationId);

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));
            // success message!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Deleting reservation failed: ", e);
        }
    }
}
