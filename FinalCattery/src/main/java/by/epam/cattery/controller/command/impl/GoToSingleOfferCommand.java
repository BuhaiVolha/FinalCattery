package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import sun.plugin.com.Dispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToSingleOfferCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String operation = request.getParameter("operation");

        OfferService offerService = ServiceFactory.getInstance().getOfferService();
        Offer offer;

        try {
            String offerId = request.getParameter("offerId");
            offer = offerService.showSingleOffer(offerId);

            if (offer != null) {
                session.setAttribute("singleOffer", offer);
                session.setAttribute("offerId", offerId);
                session.setAttribute("userMadeOfferId", offer.getUserMadeOfferId());
            }

        } catch (ServiceException e) {
            System.out.println(e + "22222");
        }

        request.getRequestDispatcher(ConfigurationManager.getProperty("path.page." + operation)).forward(request, response);
    }
}
