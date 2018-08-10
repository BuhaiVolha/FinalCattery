package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
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

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");
    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");


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

        cat.setId(Integer.parseInt(requestContent.getParameter("catId")));
        cat.setName(requestContent.getParameter("name"));
        cat.setLastname(requestContent.getParameter("lastname"));
        cat.setGender(Gender.valueOf(requestContent.getParameter("gender")));
        cat.setAge(requestContent.getParameter("age"));
        cat.setPrice(Double.parseDouble(requestContent.getParameter("price")));
        cat.setDescription(requestContent.getParameter("description"));
        cat.setBodyColour(requestContent.getParameter("bodyColour"));
        cat.setEyesColour(requestContent.getParameter("eyesColour"));
        cat.setFemaleParent(requestContent.getParameter("femaleParent"));
        cat.setMaleParent(requestContent.getParameter("maleParent"));
        cat.setPrice(Double.parseDouble(requestContent.getParameter("price")));

        return cat;
    }
}
