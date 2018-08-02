package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetColourPreferenceCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SetColourPreferenceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        try {
            User user = new User();                 // DTO   ???????????/

            user.setColourPreference(request.getParameter("colour"));
            user.setId((int) session.getAttribute("userId"));

            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.changeColourPreference(user);

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));
            // success message!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        } catch (Exception e) {
            logger.log(Level.ERROR, "Setting colour preference failed: ", e);
        }
    }
}
