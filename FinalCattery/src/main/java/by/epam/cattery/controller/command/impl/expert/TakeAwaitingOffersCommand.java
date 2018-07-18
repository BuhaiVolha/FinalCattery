package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;
import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TakeAwaitingOffersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAwaitingOffersCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            List<Offer> offers = null;

            OfferService offerService = ServiceFactory.getInstance().getOfferService();
            offers = offerService.takeAllOffersByStatus(OfferStatus.AWAIT.toString());
            request.setAttribute("catsByStatus", offers);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.assessment")).forward(request, response);

        } catch (ServiceException e) {
            //redirect
            logger.log(Level.ERROR, "Exception while showing awaiting offers for expert: ", e);
        }
    }
}
