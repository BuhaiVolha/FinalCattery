package by.epam.cattery.controller.command.impl.expert;

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

public class DeclineOfferCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Offer offer = (Offer) session.getAttribute("singleOffer");
        offer.setExpertMessage(request.getParameter("expertMessage"));

        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        try {
            offerService.answerToOffer(offer, OfferStatus.REJCT.toString(), false);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));
            // success message!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        } catch (ServiceException e) {
            System.out.println("smth bad happened " + e);
        }
    }
}
