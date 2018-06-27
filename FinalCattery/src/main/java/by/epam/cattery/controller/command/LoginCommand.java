package by.epam.cattery.controller.command;

import by.epam.cattery.entity.User;
import by.epam.cattery.resource.ConfigurationManager;
import by.epam.cattery.resource.MessageManager;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    //public String execute(HttpServletRequest request, HttpServletResponse response) {
//    public void execute(HttpServletRequest request, HttpServletResponse response) {
//        String page = null;
//
//        String login = request.getParameter(PARAM_NAME_LOGIN);
//        String password = request.getParameter(PARAM_NAME_PASSWORD);
//
//        try {
//            UserService userService = ServiceFactory.getInstance().getUserService();
//            User user = userService.logIn(login, password);
//
//            if (user != null) {
//                HttpSession session = request.getSession();
//                session.setAttribute("userId", user.getId());
//                session.setAttribute("login", user.getUserLogin());
//
//                request.setAttribute("user", login);
//                page = ConfigurationManager.getProperty("path.page.welcome");
//
//            } else {
//                request.setAttribute("errorLoginPassMessage",
//                        MessageManager.getProperty("message.loginerror"));
//                page = ConfigurationManager.getProperty("path.page.login");
//            }
//        } catch (Exception e) {
//
//        }
//        //return page;
//    }


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

                response.sendRedirect(ConfigurationManager.getProperty("path.page.welcome"));

            } else {
                request.setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty("message.loginerror"));
                request.getRequestDispatcher(ConfigurationManager
                        .getProperty("path.page.login")).forward(request, response);
            }
        } catch (ServiceException e) {
            request.getRequestDispatcher(ConfigurationManager
                    .getProperty("path.page.error")).forward(request, response);
        }
    }
}
