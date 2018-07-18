package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.service.OfferService;
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

public class ShowAllOffersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ShowAllOffersCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Offer> offers = null;
        HttpSession session = request.getSession();

        try {
            OfferService offerService = ServiceFactory.getInstance().getOfferService();
            offers = offerService.takeAllOffersByUserId(session.getAttribute("userId").toString());

            request.setAttribute("catOffers", offers);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.user-offers")).forward(request, response);

        } catch (ServiceException e) {
            //redirect
            logger.log(Level.ERROR, "Can't show offers: ", e);
        }
    }
}
