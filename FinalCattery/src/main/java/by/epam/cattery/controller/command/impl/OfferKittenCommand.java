package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;
import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OfferKittenCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Offer offer = createOffer(request, session);

        OfferService offerService = ServiceFactory.getInstance().getOfferService();
        try {
            offerService.offerKitten(offer);
            // а ид???
            session.setAttribute("userId", offer.getUserMadeOfferId());
            session.setAttribute("name", offer.getUserMadeOfferName());
            session.setAttribute("lastname", offer.getUserMadeOfferLastname());
            session.setAttribute("catDescription", offer.getCatDescription());
            session.setAttribute("price", offer.getPrice());
            session.setAttribute("phone", offer.getUserMadeOfferPhone());
            session.setAttribute("status", offer.getStatus());
            // ExpertMessage?

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));
            // success message!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        } catch (ServiceException e) {
            System.out.println("smth bad happened " + e);

        }
    }

    private Offer createOffer(HttpServletRequest request, HttpSession session) {
        Offer offer = new Offer();
// offer id?????

        offer.setUserMadeOfferId((int)(session.getAttribute("userId")));
        offer.setUserMadeOfferName((String)session.getAttribute("name"));
        offer.setUserMadeOfferLastname((String)session.getAttribute("lastname"));
        offer.setUserMadeOfferPhone((String)session.getAttribute("phone"));
        offer.setCatDescription(request.getParameter("catDescription"));
        offer.setPrice(Double.parseDouble(request.getParameter("price")));
        offer.setStatus(OfferStatus.AWAITING);

        return offer;
    }
}
