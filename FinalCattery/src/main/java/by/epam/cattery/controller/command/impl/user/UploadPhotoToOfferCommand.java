package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.util.UploadHelper;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;

public class UploadPhotoToOfferCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UploadPhotoToOfferCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");
    private static final String OFFER_PHOTO_SAVE_PATH = ConfigurationManager.getInstance().getProperty("path.photo.offer");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        int offerId = Integer.parseInt(requestContent.getParameter("offerId"));
        Part filePart = requestContent.getPart("offer");
        String prefixToName = "offer";

        offerService.addPhotoToOffer(offerId, UploadHelper.getInstance().upload(filePart, OFFER_PHOTO_SAVE_PATH, prefixToName));

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE); //Обратно в кабинет
    }
}