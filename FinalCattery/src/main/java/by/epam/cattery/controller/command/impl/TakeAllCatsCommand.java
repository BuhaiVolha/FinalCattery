package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.Role;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
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

public class TakeAllCatsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllCatsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Cat> cats = null;
        HttpSession session = request.getSession();

        try {
            CatService catService = ServiceFactory.getInstance().getCatService();

            if (session.getAttribute("role") == Role.USER) {
                logger.log(Level.DEBUG, "User is logged in, showing cats with discount");

                int userId = Integer.parseInt(session.getAttribute("userId").toString());
                cats = catService.takeAllCatsWithDiscount(userId);

            } else {
                logger.log(Level.DEBUG, "Showing just prices without any discount");
                cats = catService.takeAllCats();
            }


        } catch (ServiceException e) {
            //redirect
            logger.log(Level.ERROR, "Cat's are not here: ", e);
        }

        request.setAttribute("cats", cats);
        request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.cats")).forward(request, response);
    }
}
