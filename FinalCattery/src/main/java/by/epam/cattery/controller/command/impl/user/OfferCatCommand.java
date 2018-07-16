package by.epam.cattery.controller.command.impl.user;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OfferCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(OfferCatCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        Offer offer = createOffer(request);  // DTO?
        offer.setUserMadeOfferId((int)(session.getAttribute("userId")));

        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        try {
            offerService.offerCat(offer);

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));
            // success message!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Offering cat failed: ", e);
        }
    }


    private Offer createOffer(HttpServletRequest request) {
        Offer offer = new Offer();

        offer.setCatDescription(request.getParameter("catDescription"));
        offer.setPrice(Double.parseDouble(request.getParameter("price")));
        offer.setStatus(OfferStatus.AWAIT);

        return offer;
    }
}
