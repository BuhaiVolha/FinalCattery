package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.entity.Role;
import by.epam.cattery.entity.User;
import by.epam.cattery.resource.ConfigurationManager;
import by.epam.cattery.resource.MessageManager;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;

import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.UserAlreadyExistsException;
import by.epam.cattery.service.exception.ValidationFailedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class RegistrationCommand implements ActionCommand {
    // protected?
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = createUser(request);
        HttpSession session = request.getSession();
        //int userId = -1;
        //StringBuilder pathToPage = new StringBuilder();

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            userService.register(user);
            // а ид???
            session.setAttribute("login", user.getUserLogin());
            session.setAttribute("role", user.getUserRole());
            session.setAttribute("name", user.getUserName());
            session.setAttribute("lastname", user.getUserLastname());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("discount", user.getDiscount());
            session.setAttribute("banned", user.isBanned());
            response.sendRedirect(ConfigurationManager.getProperty("path.page.successful-reg"));

        } catch (ValidationFailedException e) {
            // заменить на бул?
            // пароль короткий
            System.out.println("vallidation failed");

        } catch (UserAlreadyExistsException e) {
            request.setAttribute("errorLoginExistsMessage",
                    MessageManager.getProperty("message.loginexists"));
            request.getRequestDispatcher(ConfigurationManager.
                    getProperty("path.page.reg")).forward(request, response);

        } catch (ServiceException e) {
            System.out.println("smth bad happened");

        }
    }


    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setUserLogin(request.getParameter("login"));
        user.setUserPass(request.getParameter("password"));
        user.setUserName(request.getParameter("name"));
        user.setUserLastname(request.getParameter("lastname"));
        user.setEmail(request.getParameter("email"));
        user.setDiscount(0);
        user.setBanned(false);
        user.setUserRole(Role.USER);

        return user;
    }
}
