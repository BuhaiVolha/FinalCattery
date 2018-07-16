package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.OfferService;
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
import java.util.List;


public class ShowAllUsersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ShowAllUsersCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> users;

        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            users = userService.showAllUser();

            request.setAttribute("users", users);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.manage-users")).forward(request, response);

        } catch (ServiceException e) {
            //redirect
            logger.log(Level.ERROR, "Can't show offers: ", e);
        }
    }
}
