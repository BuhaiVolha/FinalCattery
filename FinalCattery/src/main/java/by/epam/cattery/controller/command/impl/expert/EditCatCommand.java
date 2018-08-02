package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Gender;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EditCatCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            Cat cat = createCat(request);

            CatService catService = ServiceFactory.getInstance().getCatService();
            catService.editCat(cat);

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page")); //Обратно на стр с котанами

        } catch (ValidationFailedException e) { // ккаято валидация
            logger.log(Level.WARN, "Validation failed: ", e);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Something pretty bad has happened: ", e);
        }
    }


    private Cat createCat(HttpServletRequest request) {
        Cat cat = new Cat();

        cat.setId(Integer.parseInt(request.getParameter("catId")));
        cat.setName(request.getParameter("name"));
        cat.setLastname(request.getParameter("lastname"));
        cat.setGender(Gender.valueOf(request.getParameter("gender")));
        cat.setAge(request.getParameter("age"));
        cat.setPrice(Double.parseDouble(request.getParameter("price")));
        cat.setDescription(request.getParameter("description"));
        cat.setBodyColour(request.getParameter("bodyColour"));
        cat.setEyesColour(request.getParameter("eyesColour"));
        cat.setFemaleParent(request.getParameter("femaleParent"));
        cat.setMaleParent(request.getParameter("maleParent"));
        cat.setPrice(Double.parseDouble(request.getParameter("price")));

        return cat;
    }
}
