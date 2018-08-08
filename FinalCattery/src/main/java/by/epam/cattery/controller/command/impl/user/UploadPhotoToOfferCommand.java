package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.util.UploadHelper;
import by.epam.cattery.util.ConfigurationManager;
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
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadPhotoToOfferCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UploadPhotoToOfferCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        try {
            int offerId = Integer.parseInt(request.getParameter("offerId"));

            Part filePart = request.getPart("file");
            String prefixToName = "offer";

            offerService.addPhotoToOffer(offerId, UploadHelper.getInstance()
                    .upload(filePart, ConfigurationManager.getInstance().getProperty("path.photo.offer"), prefixToName));

            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.success-page")); //Обратно в кабинет

        } catch (ServiceException e) {
            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.error"));
            logger.log(Level.ERROR, "Something pretty bad has happened: ", e);
        }
    }
}