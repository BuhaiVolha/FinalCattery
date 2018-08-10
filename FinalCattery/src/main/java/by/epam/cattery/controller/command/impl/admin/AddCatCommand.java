package by.epam.cattery.controller.command.impl.admin;

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
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


public class AddCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddCatCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();

        Cat cat = createCat(requestContent);

        if (requestContent.getParameter("offerId").isEmpty()) {
            cat.setOfferMadeId(1);
            cat.setUserMadeOfferId(1);

            catService.addCat(cat);

        } else {
            cat.setOfferMadeId(Integer.parseInt(requestContent.getParameter("offerId")));
            cat.setUserMadeOfferId(Integer.parseInt(requestContent.getParameter("userMadeOfferId")));
            String filename = requestContent.getParameter("photo");
            copyOfferPhotoToCats(filename);
            cat.setPhoto(filename);

            catService.addOfferedCat(cat, cat.getOfferMadeId());
        }
        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }


    // впихнуть билдер сюда для юзерного и админного кота?
    private Cat createCat(RequestContent requestContent) {
        Cat cat = new Cat();

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

        return cat;
    }

// отдельный методо типа перенести файл с одного на другое папку
    private void copyOfferPhotoToCats(String filename) {
        try {
            File file = new File(ConfigurationManager.getInstance().getProperty("path.photo.offer") + filename);
            File dest = new File(ConfigurationManager.getInstance().getProperty("path.photo.cat"));
            FileUtils.copyFileToDirectory(file, dest);

        } catch (IOException e) {
            logger.log(Level.WARN, "Failed to move offer photo to cats", e);
        }
    }
}
