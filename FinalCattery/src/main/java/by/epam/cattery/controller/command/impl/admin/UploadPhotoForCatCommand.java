package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
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

public class UploadPhotoForCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UploadPhotoForCatCommand.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");
    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");
    private static final String CAT_PHOTO_SAVE_PATH = ConfigurationManager.getInstance().getProperty("path.photo.cat");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();

        int catId = Integer.parseInt(requestContent.getParameter("catId"));
        Part filePart = requestContent.getPart("cat");
        String prefixToName = "cat-up";

        catService.addCatPhoto(catId, UploadHelper.getInstance().upload(filePart, CAT_PHOTO_SAVE_PATH, prefixToName));

        //Обратно в кабинет
        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }
}
