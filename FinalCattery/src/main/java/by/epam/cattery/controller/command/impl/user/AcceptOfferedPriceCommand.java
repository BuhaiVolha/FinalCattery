package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;
import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcceptOfferedPriceCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AcceptOfferedPriceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            OfferService offerService = ServiceFactory.getInstance().getOfferService();

            Offer offer = new Offer(); // не в объект а отдельный ДТО?
            offer.setId(Integer.parseInt(request.getParameter("offerId")));
            offer.setPrice(Double.parseDouble(request.getParameter("price")));
            offer.setStatus(OfferStatus.APRVD);

            offerService.answerToOffer(offer, OfferStatus.DISC);

            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.success-page"));

        } catch (ServiceException | NumberFormatException e) {
            logger.log(Level.ERROR, "Exception while accepting price: ", e);
            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.error"));
        }
    }
}
