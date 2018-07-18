package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSingleCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleCatCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            CatService catService = ServiceFactory.getInstance().getCatService();

            Cat cat;
            int catId = Integer.parseInt(request.getParameter("catId"));
            cat = catService.takeSingleCat(catId);

            if (cat != null) {
                request.setAttribute("singleCat", cat);
            }

            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.single-cat")).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to go to a single offer: ", e);
        }
    }
}
