package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class CountPedigreeCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(CountPedigreeCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            ReservationService reservationService = ServiceFactory.getInstance().getReservationService();
            Map<CatPedigreeType, Integer> pedigreeCount = reservationService.countPedigree();

            request.setAttribute("pedigreeCount", pedigreeCount);

            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.pedigree")).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Showing statistics failed: ", e);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }
    }
}
