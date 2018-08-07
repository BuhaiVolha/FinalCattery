package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeDiscountCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(BanUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            int discount = Integer.parseInt(request.getParameter("discount")); // DTO ?
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = new User();
            user.setId(userId);
            user.setDiscount(discount);

            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.makeDiscount(user);

            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.success-page"));

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Making discount failed: ", e);
        }
    }
}
