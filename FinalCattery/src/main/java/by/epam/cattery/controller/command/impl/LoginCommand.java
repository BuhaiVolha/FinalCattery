package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.entity.User;
import by.epam.cattery.resource.ConfigurationManager;
import by.epam.cattery.resource.MessageManager;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.http.client.utils.URIBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";


    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        HttpSession session = request.getSession();

        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.logIn(login, password);

            if (user != null) {
                session.setAttribute("userId", user.getId());
                session.setAttribute("login", user.getUserLogin());
                session.setAttribute("role", user.getUserRole());
                session.setAttribute("name", user.getUserName());
                session.setAttribute("lastname", user.getUserLastname());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("colorPreference", user.getUserColorPreference());
                session.setAttribute("discount", user.getDiscount());
                session.setAttribute("banned", user.isBanned());

                response.sendRedirect(ConfigurationManager.getProperty("path.page.welcome"));

            } else {
//                request.setAttribute("errorLoginPassMessage",
//                        MessageManager.getProperty("message.loginerror"));
//                request.getRequestDispatcher(ConfigurationManager
//                        .getProperty("path.page.main")).forward(request, response);

                request.getSession(true).setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty("message.loginerror"));
                response.sendRedirect(ConfigurationManager.getProperty("path.page.main"));

//                StringBuilder viewPath = new StringBuilder();
//                viewPath.append(getUr(request));
//                response.sendRedirect(viewPath.toString());

            }
        } catch (ServiceException e) {
            System.out.println("errorrrrrrrr");
//            request.getRequestDispatcher(ConfigurationManager
//                    .getProperty("path.page.error")).forward(request, response);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }
    }

    private URI getUr(HttpServletRequest request) {
        try {
            String refererPage = request.getHeader("referer");
            URI uriWithParam = new URIBuilder(refererPage).setParameter("message", "signInfailed").build();
            return uriWithParam;
        } catch (URISyntaxException e) {
            System.out.println("except");
            throw new RuntimeException(e);
        }
    }
}
