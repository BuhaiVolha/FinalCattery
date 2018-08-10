package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
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

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.ERROR_PAGE);
    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();

        try {
            Cat cat = createCat(requestContent);
            catService.editCat(cat);

            //Обратно на стр с котанами
            return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);

        } catch (ValidationFailedException e) { // ккаято валидация
            logger.log(Level.WARN, "Validation failed: ", e);
            return new RequestResult(NavigationType.REDIRECT, ERROR_PAGE); // Обратно на форму
        }
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
