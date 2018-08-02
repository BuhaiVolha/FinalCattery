package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.User;
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

public class GoToCabinetCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToCabinetCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user;

        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            int userId = (int) session.getAttribute("userId");
            user = userService.takeUser(userId);

            request.setAttribute("user", user);

            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.user-info")).forward(request, response);

        } catch (ServiceException e) {
            //redirect
            logger.log(Level.ERROR, "Showing user cabinet failed: ", e);
        }
    }
}
