package by.epam.cattery.controller.command;

import by.epam.cattery.entity.User;
import by.epam.cattery.resource.ConfigurationManager;
import by.epam.cattery.resource.MessageManager;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements ActionCommand {

    //public String execute(HttpServletRequest request, HttpServletResponse response) {
//    public void execute(HttpServletRequest request, HttpServletResponse response) {
//        String page = null;
//
//        User user = createUser(request);
//        HttpSession session = request.getSession();
//        StringBuilder pathToPage = new StringBuilder(); //
//
//        try {
//            UserService userService = ServiceFactory.getInstance().getUserService();
//            int userId = userService.register(user);
//
//            if (userId != -1) {
//                System.out.println("in");
//
//                session.setAttribute("userId", userId);
//                session.setAttribute("login", user.getUserLogin());
//
//                request.setAttribute("user", user.getUserLogin());
//                page = ConfigurationManager.getProperty("path.page.successful-reg");
//
//            } else {
//                System.out.println("not in");
//                request.setAttribute("errorLoginExistsMessage",
//                        MessageManager.getProperty("message.loginexists"));
//                page = ConfigurationManager.getProperty("path.page.reg");
//            }
//
//        } catch (Exception e) {
//            System.out.println("error " + e);
//        }
//        //return page;
//    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = createUser(request);
        HttpSession session = request.getSession();
        int userId = -1;
        StringBuilder pathToPage = new StringBuilder(); //

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            userId = userService.register(user);
        } catch (ServiceException | ValidationFailedException e) {
            // заменить на бул?
        }

        if (userId != -1) {
            session.setAttribute("userId", userId);
            session.setAttribute("login", user.getUserLogin());
            //request.getSession().setAttribute("user", user.getUserLogin());

            response.sendRedirect(ConfigurationManager.getProperty("path.page.successful-reg"));

        } else {
            request.setAttribute("errorLoginExistsMessage",
                    MessageManager.getProperty("message.loginexists"));
            request.getRequestDispatcher(ConfigurationManager.
                    getProperty("path.page.reg")).forward(request, response);
        }
    }


    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setUserLogin(request.getParameter("login"));
        user.setUserPass(request.getParameter("password"));

        return user;
    }
}
