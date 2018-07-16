package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowStatisticsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ShowStatisticsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            String statistics = userService.showStatistics();
            session.setAttribute("colourStatistics", statistics);

            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.statistics")).forward(request, response);

        } catch (Exception e) {
            logger.log(Level.ERROR, "Showing statistics failed: ", e);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }
    }
}
