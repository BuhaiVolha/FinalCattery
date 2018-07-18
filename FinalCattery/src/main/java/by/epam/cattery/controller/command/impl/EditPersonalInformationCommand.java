package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.controller.util.MessageManager;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.EmailAlreadyExistsException;
import by.epam.cattery.service.exception.LoginAlreadyExistsException;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditPersonalInformationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EditPersonalInformationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            User user = createUser(request);

            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.editPersonalInfo(user);

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page")); //Обратно в кабинет

        } catch (ValidationFailedException e) {
            // заменить на бул?
            // пароль короткий
            logger.log(Level.WARN, "Validation failed: ", e);

        } catch (LoginAlreadyExistsException e) {
            logger.log(Level.WARN, "User already exists exception: ", e);

            request.setAttribute("errorLoginExistsMessage", MessageManager.getProperty("message.loginexists"));
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.edit-user-info")).forward(request, response);

        } catch (EmailAlreadyExistsException e) {
            request.setAttribute("errorEmailExistsMessage", MessageManager.getProperty("message.emailtaken"));
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.edit-user-info")).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Something pretty bad has happened: ", e);
        }
    }


    private User createUser(HttpServletRequest request) {
        User user = new User();
        HttpSession session = request.getSession();

        user.setId(Integer.parseInt(session.getAttribute("userId").toString()));
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastname"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        System.out.println(request.getParameter("password") + " password from command");
        user.setPassword(request.getParameter("password"));

        return user;
    }
}
