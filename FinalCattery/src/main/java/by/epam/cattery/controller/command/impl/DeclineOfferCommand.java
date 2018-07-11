package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
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
        System.out.println(session.getAttribute("offerId") + "sess");
        String id = request.getParameter("offerId");
        System.out.println(id + " ID");

        OfferService offerService = ServiceFactory.getInstance().getOfferService();
        try {
            offerService.declineOffer(id, request.getParameter("expertMessage"), "REJECTED");

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));
            // success message!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

        } catch (ServiceException e) {
            System.out.println("smth bad happened " + e);
        }
    }
}
