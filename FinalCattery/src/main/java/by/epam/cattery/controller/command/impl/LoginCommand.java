package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.entity.User;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.UserIsBannedException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";


    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        HttpSession session = request.getSession();
        String viewPath = request.getHeader("referer");

        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.logIn(login, password);

            if (user != null) {
                session.setAttribute("userId", user.getId());
                session.setAttribute("login", user.getLogin());
                session.setAttribute("role", user.getRole());

                response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.success-page"));

            } else {
                request.getSession(true).setAttribute("errorLoginPassMessage",
                        ConfigurationManager.getInstance().getMessage("message.loginerror"));
                response.sendRedirect(viewPath);

            }

        } catch (UserIsBannedException e) {
            request.getSession(true).setAttribute("errorLoginPassMessage",
                    ConfigurationManager.getInstance().getMessage("message.userbanned"));
            response.sendRedirect(viewPath);

        }  catch (ServiceException e) {
            logger.log(Level.ERROR, "Logging in failed: ", e);
            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.error"));
        }
    }
}
