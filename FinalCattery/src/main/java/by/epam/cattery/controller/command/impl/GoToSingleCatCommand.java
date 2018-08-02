package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.Role;
import by.epam.cattery.service.CatService;
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

// подумать с дискаунтами
public class GoToSingleCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleCatCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operation = request.getParameter("operation");

        try {
            HttpSession session = request.getSession();
            CatService catService = ServiceFactory.getInstance().getCatService();

            Cat cat;
            int catId = Integer.parseInt(request.getParameter("catId"));
            int discountPercents = 0;

            cat = catService.takeSingleCat(catId);

            if (session.getAttribute("role") == Role.USER) {
                int userId = (int) session.getAttribute("userId");
                UserService userService = ServiceFactory.getInstance().getUserService();

                discountPercents = userService.getDiscount(userId);
                cat.setPriceWithDiscount(cat.getPrice() - (cat.getPrice() * discountPercents) / 100);
            } else {
                cat.setPriceWithDiscount(cat.getPrice());
            }

            request.setAttribute("singleCat", cat);

            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page." + operation)).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to go to a single offer: ", e);
        }
    }
}
