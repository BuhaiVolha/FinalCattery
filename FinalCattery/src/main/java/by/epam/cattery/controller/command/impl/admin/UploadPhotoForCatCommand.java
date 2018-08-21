package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.util.UploadHelper;

import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.util.ConfigurationManager;

import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;


/**
 * The command for uploading a photo for cat.
 *
 */
public class UploadPhotoForCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UploadPhotoForCatCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);
    private static final String CAT_PHOTO_SAVE_PATH = ConfigurationManager.getInstance()
            .getProperty(PathConst.CAT_PHOTO_SAVE_PATH);
    private static final String CAT_PHOTO_PREFIX = "cat-up";


    /**
     *
     * Receives photo from {@code requestContent}, generates random name for photo using {@code CAT_PHOTO_PREFIX},
     * saves photo to {@code CAT_PHOTO_SAVE_PATH}, the name - to the cat.
     * Redirects to the success page.
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();

        int catId = Integer.parseInt(requestContent.getParameter(RequestConst.CAT_ID));
        Part filePart = requestContent.getPart(RequestConst.CAT_PHOTO_PARTNAME);
        catService.addCatPhoto(catId, UploadHelper.getInstance().upload(filePart, CAT_PHOTO_SAVE_PATH, CAT_PHOTO_PREFIX));

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }
}
