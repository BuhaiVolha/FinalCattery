package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.util.UploadHelper;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UploadPhotoForCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UploadPhotoForCatCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CatService catService = ServiceFactory.getInstance().getCatService();

        try {
            int catId = Integer.parseInt(request.getParameter("catId"));
            Part filePart = request.getPart("file");
            String prefixToName = "cat-up";

            catService.addCatPhoto(catId, UploadHelper.getInstance()
                    .upload(filePart, ConfigurationManager.getInstance().getProperty("path.photo.cat"), prefixToName));

            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.success-page")); //Обратно в кабинет

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Something pretty bad has happened: ", e);
        }
    }
}
