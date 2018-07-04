package by.epam.cattery.controller.command;

import by.epam.cattery.entity.Role;
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
import java.io.PrintWriter;

public class RegistrationCommand implements ActionCommand {
    // protected?
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = createUser(request);
        HttpSession session = request.getSession();
        int userId = -1;
        //StringBuilder pathToPage = new StringBuilder();

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            userId = userService.register(user);
        } catch (ServiceException | ValidationFailedException e) {
            // заменить на бул?
            // пароль короткий
        }

        if (userId != -1) {
            session.setAttribute("userId", userId);
            session.setAttribute("login", user.getUserLogin());
            session.setAttribute("role", Role.USER);
            // oстальные?

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
        user.setUserName(request.getParameter("name"));
        user.setUserLastname(request.getParameter("lastname"));
        user.setEmail(request.getParameter("email"));
        user.setUserRole(Role.USER);
        // роль

        return user;
    }
}
