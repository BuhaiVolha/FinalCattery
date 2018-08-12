package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.MessageConst;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.util.PathHelper;

import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.service.exception.InvalidDateException;
import by.epam.cattery.util.ConfigurationManager;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Gender;

import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EditCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EditCatCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);
    private static final String EDIT_CAT_COMMAND = ConfigurationManager.getInstance().getProperty(PathConst.EDIT_CAT_COMMAND);

    private static final String INVALID_INPUT_MESSAGE = ConfigurationManager.getInstance().getMessage(MessageConst.INVALID_INPUT);
    private static final String INVALID_BIRTH_DATE_MESSAGE = ConfigurationManager.getInstance().getMessage(MessageConst.INVALID_BIRTH_DATE);


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();
        Cat cat = createCat(requestContent);

        PathHelper pathHelper = PathHelper.getInstance();
        String path;

        try {
            catService.editCat(cat);

            //Обратно на стр с котанами
            path = SUCCESS_PAGE;

        } catch (ValidationFailedException e) {
            logger.log(Level.WARN, "Validation of input data failed during adding cat");
            path = pathHelper.addParameterToPath(EDIT_CAT_COMMAND,
                    RequestConst.SENDING_CAT_FORM_FAILED_MESSAGE,
                    INVALID_INPUT_MESSAGE);
            path = pathHelper.addParameterToPath(path,
                    RequestConst.CAT_ID,
                    cat.getId());

        } catch (InvalidDateException e) {
            logger.log(Level.WARN, "Validation of input birthday failed during adding cat");
            path = pathHelper.addParameterToPath(EDIT_CAT_COMMAND,
                    RequestConst.SENDING_CAT_FORM_FAILED_MESSAGE,
                    INVALID_BIRTH_DATE_MESSAGE);
            path = pathHelper.addParameterToPath(path,
                    RequestConst.CAT_ID,
                    cat.getId());
        }
        return new RequestResult(NavigationType.REDIRECT, path);
    }


    private Cat createCat(RequestContent requestContent) {
        Cat cat = new Cat();

        cat.setId(Integer.parseInt(requestContent.getParameter(RequestConst.CAT_ID)));
        cat.setName(requestContent.getParameter(RequestConst.CAT_NAME));
        cat.setLastname(requestContent.getParameter(RequestConst.CAT_LASTNAME));
        cat.setGender(Gender.valueOf(requestContent.getParameter(RequestConst.CAT_GENDER)));
        cat.setAge(requestContent.getParameter(RequestConst.CAT_AGE));
        cat.setPrice(Double.parseDouble(requestContent.getParameter(RequestConst.CAT_PRICE)));
        cat.setDescription(requestContent.getParameter(RequestConst.CAT_DESCRIPTION));
        cat.setBodyColour(requestContent.getParameter(RequestConst.CAT_BODY_COLOUR));
        cat.setEyesColour(requestContent.getParameter(RequestConst.CAT_EYES_COLOUR));
        cat.setFemaleParent(requestContent.getParameter(RequestConst.CAT_FEMALE_PARENT));
        cat.setMaleParent(requestContent.getParameter(RequestConst.CAT_MALE_PARENT));

        return cat;
    }
}
