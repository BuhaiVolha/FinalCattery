package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
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

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);
    private static final String OFFER_PHOTO_SAVE_PATH = ConfigurationManager.getInstance().getProperty(PathConst.OFFER_PHOTO_SAVE_PATH);
    private static final String OFFER_PHOTO_PREFIX = "offer";


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        int offerId = Integer.parseInt(requestContent.getParameter(RequestConst.OFFER_ID));
        Part filePart = requestContent.getPart(RequestConst.OFFER_PHOTO_PARTNAME);
        offerService.addPhotoToOffer(offerId, UploadHelper.getInstance()
                .upload(filePart, OFFER_PHOTO_SAVE_PATH, OFFER_PHOTO_PREFIX));

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE); //Обратно в кабинет
    }
}