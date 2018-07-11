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
import java.util.List;

public class ShowOffersByStatusCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Offer> offers = null;
        HttpSession session = request.getSession();
        String status = request.getParameter("status").toUpperCase();

        try {
            OfferService offerService = ServiceFactory.getInstance().getOfferService();
            offers = offerService.showAllOffersByStatus(status);

        } catch (ServiceException e) {
            //redirect
            System.out.println(e);
            System.out.println("offers by status aren't here!");
        }

        session.setAttribute("awaitingKittenOffers", offers);
        response.sendRedirect(ConfigurationManager.getProperty("path.page.assessment"));
    }


//    private String definePage(String status) {
//
//        switch (status) {
//            case OfferStatus.AWAITING:
//                return "path.page.assessment"; break;
//        }
//    }
}
