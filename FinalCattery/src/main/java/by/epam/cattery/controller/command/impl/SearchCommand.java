package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.*;
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
import java.util.List;

public class SearchCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SearchCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Cat> cats = null;
        HttpSession session = request.getSession();

        try {
            Cat cat = createCat(request);
            int discountPercents;

            CatService catService = ServiceFactory.getInstance().getCatService();
            cats = catService.searchForCat(cat);

            if (session.getAttribute("role") == Role.USER) {
                int userId = (int) session.getAttribute("userId");
                UserService userService = ServiceFactory.getInstance().getUserService();

                discountPercents = userService.getDiscount(userId);
                request.setAttribute("discount", discountPercents);
            }


            request.setAttribute("cats", cats);
            request.getRequestDispatcher(ConfigurationManager.getInstance()
                    .getProperty("path.page.cats")).forward(request, response);

        } catch (ServiceException e) {
            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.error"));
            logger.log(Level.ERROR, "Cat's are not here: ", e);
        }
    }


    private Cat createCat(HttpServletRequest request) {
        Cat cat = new Cat();

        if (!request.getParameter("price").isEmpty()) {
            cat.setPrice(Double.parseDouble(request.getParameter("price")));
        }

        if (!request.getParameter("gender").isEmpty()) {
            cat.setGender(Gender.valueOf(request.getParameter("gender")));
        }
        if (!request.getParameter("status").isEmpty()) {
            cat.setStatus(CatStatus.valueOf(request.getParameter("status")));
        }
        cat.setBodyColour(request.getParameter("body"));
        cat.setEyesColour(request.getParameter("eyes"));

        return cat;
    }
}