package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
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

public class GoToSingleOfferCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleOfferCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operation = request.getParameter("operation");

        OfferService offerService = ServiceFactory.getInstance().getOfferService();
        Offer offer;

        try {
            int offerId = Integer.parseInt(request.getParameter("offerId"));
            offer = offerService.takeSingleOffer(offerId);

            if (offer != null) {
                request.setAttribute("offer", offer);
            }

            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page." + operation)).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to go to a single offer: ", e);
            //redirect
        }
    }
}
