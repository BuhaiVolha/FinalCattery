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

        try {
            OfferService offerService = ServiceFactory.getInstance().getOfferService();

            Offer offer = new Offer(); // не в объект а отдельный ДТО?
            offer.setExpertMessage(request.getParameter("expertMessage"));
            offer.setId(Integer.parseInt(request.getParameter("offerId")));

            offerService.answerToOffer(offer, OfferStatus.REJCT, false);

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));
            // success message!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        } catch (Exception e) {
            System.out.println("smth bad happened " + e);
        }
    }
}
