package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
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
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


public class AddCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddCatCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Cat cat = createCat(request);
            CatService catService = ServiceFactory.getInstance().getCatService();

            if (request.getParameter("offerId").isEmpty()) {
                cat.setOfferMadeId(1);
                cat.setUserMadeOfferId(1);

                catService.addCat(cat);

            } else {
                cat.setOfferMadeId(Integer.parseInt(request.getParameter("offerId")));
                cat.setUserMadeOfferId(Integer.parseInt(request.getParameter("userMadeOfferId")));
                String filename = request.getParameter("photo");
                copyOfferPhotoToCats(filename);
                cat.setPhoto(filename);


                catService.addOfferedCat(cat, cat.getOfferMadeId());
            }

            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.success-page"));

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to add a cat");
            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.error"));
        }
    }


// впихнуть билдер сюда для юзерного и админного кота?
    private Cat createCat(HttpServletRequest request) {
        Cat cat = new Cat();

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

        return cat;
    }


    private void copyOfferPhotoToCats(String filename) throws IOException {
        File file = new File(ConfigurationManager.getInstance().getProperty("path.photo.offer") + filename);
        File dest = new File(ConfigurationManager.getInstance().getProperty("path.photo.cat"));
        FileUtils.copyFileToDirectory(file, dest);
    }
}
