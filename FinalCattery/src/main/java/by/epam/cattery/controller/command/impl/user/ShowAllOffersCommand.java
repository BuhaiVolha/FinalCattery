package by.epam.cattery.controller.command.impl.user;

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
import java.util.List;

public class ShowAllOffersCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Offer> offers = null;
        HttpSession session = request.getSession();

        try {
            OfferService offerService = ServiceFactory.getInstance().getOfferService();
            offers = offerService.showAllOffersByUserId(session.getAttribute("userId").toString());

        } catch (ServiceException e) {
            //redirect
            System.out.println(e);
            System.out.println("offers aren't here");
        }

        session.setAttribute("catOffers", offers);
        response.sendRedirect(ConfigurationManager.getProperty("path.page.user-offers"));
    }
}
