package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.MessageConst;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.command.util.PathHelper;
import by.epam.cattery.controller.command.util.UploadHelper;

import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.entity.CatBodyColour;
import by.epam.cattery.entity.CatEyesColour;
import by.epam.cattery.service.exception.InvalidDateException;
import by.epam.cattery.service.exception.ValidationFailedException;

import by.epam.cattery.util.ConfigurationManager;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Gender;

import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AddCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddCatCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);
    private static final String CAT_FORM_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.CAT_FORM_PAGE);
    private static final String UPLOAD_CAT_PHOTO_PAGE = ConfigurationManager.getInstance()
            .getProperty(PathConst.UPLOAD_CAT_PHOTO_PAGE);
    private static final String CAT_PHOTO_SAVE_PATH = ConfigurationManager.getInstance().getProperty(PathConst.CAT_PHOTO_SAVE_PATH);
    private static final String OFFER_PHOTO_SAVE_PATH = ConfigurationManager.getInstance().getProperty(PathConst.OFFER_PHOTO_SAVE_PATH);

    private static final int ADMIN_ID = 1;


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();

        PathHelper pathHelper = PathHelper.getInstance();
        String path;

        String locale = requestContent.getSessionAttribute(SessionConst.LOCALE).toString();
        String message;

        try {
            Cat cat = createCat(requestContent);

            if (requestContent.getParameter(RequestConst.OFFER_ID).isEmpty()) {
                cat.setOfferMadeId(ADMIN_ID);
                cat.setUserMadeOfferId(ADMIN_ID);

                int catId = catService.addCat(cat);

                path = pathHelper.addParameterToPath(UPLOAD_CAT_PHOTO_PAGE, RequestConst.CAT_ID, catId);
                return new RequestResult(NavigationType.REDIRECT, path);

            } else {
                cat.setOfferMadeId(Integer.parseInt(requestContent.getParameter(RequestConst.OFFER_ID)));
                cat.setUserMadeOfferId(Integer.parseInt(requestContent.getParameter(RequestConst.OFFER_MADE_USER_ID)));
                String filename = requestContent.getParameter(RequestConst.ADDED_CAT_PHOTO);
                UploadHelper.getInstance().moveFileToAnotherDirectory(filename, OFFER_PHOTO_SAVE_PATH, CAT_PHOTO_SAVE_PATH);
                cat.setPhoto(filename);

                catService.addOfferedCat(cat, cat.getOfferMadeId());
                path = SUCCESS_PAGE;
            }

        } catch (ValidationFailedException e) {
            logger.log(Level.WARN, "Validation of input data failed during adding cat");
            message = ConfigurationManager.getInstance().getMessage(MessageConst.INVALID_INPUT, locale);
            path = pathHelper.addParameterToPath(CAT_FORM_PAGE, RequestConst.SENDING_CAT_FORM_FAILED_MESSAGE, message);

        } catch (InvalidDateException e) {
            logger.log(Level.WARN, "Validation of input birthday failed during adding cat");
            message = ConfigurationManager.getInstance().getMessage(MessageConst.INVALID_BIRTH_DATE, locale);
            path = pathHelper.addParameterToPath(CAT_FORM_PAGE, RequestConst.SENDING_CAT_FORM_FAILED_MESSAGE, message);
        }
        return new RequestResult(NavigationType.REDIRECT, path);
    }


    private Cat createCat(RequestContent requestContent) {
        Cat cat = new Cat();

        cat.setName(requestContent.getParameter(RequestConst.CAT_NAME));
        cat.setLastname(requestContent.getParameter(RequestConst.CAT_LASTNAME));
        cat.setGender(Gender.valueOf(requestContent.getParameter(RequestConst.CAT_GENDER)));
        cat.setAge(requestContent.getParameter(RequestConst.CAT_AGE));
        cat.setPrice(Double.parseDouble(requestContent.getParameter(RequestConst.CAT_PRICE)));
        cat.setDescription(requestContent.getParameter(RequestConst.CAT_DESCRIPTION));
        cat.setBodyColour(CatBodyColour.valueOf(requestContent.getParameter(RequestConst.CAT_BODY_COLOUR)));
        cat.setEyesColour(CatEyesColour.valueOf(requestContent.getParameter(RequestConst.CAT_EYES_COLOUR)));
        cat.setFemaleParent(requestContent.getParameter(RequestConst.CAT_FEMALE_PARENT));
        cat.setMaleParent(requestContent.getParameter(RequestConst.CAT_MALE_PARENT));

        return cat;
    }
}
