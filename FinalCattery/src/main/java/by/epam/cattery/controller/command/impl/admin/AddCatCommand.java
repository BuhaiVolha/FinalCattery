package by.epam.cattery.controller.command.impl.admin;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddCatCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CatService catService = ServiceFactory.getInstance().getCatService();

        try {
            Cat cat = createCat(request);
            catService.addCat(cat);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));

        } catch (ValidationFailedException e) { // doubleSubmitException?
            logger.log(Level.WARN, "No more cats! An attempt to add already added cat during sending an offer by admin");
            response.sendRedirect(ConfigurationManager.getProperty("path.page.user-info"));

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to add any cat");
        }
    }

// впихнуть билдер сюда для юзерного и админного кота?
    private Cat createCat(HttpServletRequest request) throws ServiceException, ValidationFailedException {
        Cat cat = new Cat();
        CatService catService = ServiceFactory.getInstance().getCatService();

        String offerId = request.getParameter("offerId");
        String userMadeOfferId = request.getParameter("userMadeOfferId");

        if (!(offerId.isEmpty() || userMadeOfferId.isEmpty())) {
            catService.catAlreadyAdded(Integer.parseInt(offerId));
            cat.setOfferMadeId(Integer.parseInt(request.getParameter("offerId")));
            cat.setUserMadeOfferId(Integer.parseInt(request.getParameter("userMadeOfferId")));

        } else {
            cat.setOfferMadeId(1);
            cat.setUserMadeOfferId(1);
        }
        cat.setName(request.getParameter("name"));
        cat.setLastname(request.getParameter("lastname"));
        cat.setGender(Gender.valueOf(request.getParameter("gender")));
        cat.setAge(request.getParameter("age"));
        cat.setPrice(Double.parseDouble(request.getParameter("price")));  // controller exception?
        cat.setDescription(request.getParameter("description"));
        cat.setBodyColour(request.getParameter("bodyColour"));
        cat.setEyesColour(request.getParameter("eyesColour"));
        cat.setFemaleParent(request.getParameter("femaleParent"));
        cat.setMaleParent(request.getParameter("maleParent"));

        return cat;
    }
}
