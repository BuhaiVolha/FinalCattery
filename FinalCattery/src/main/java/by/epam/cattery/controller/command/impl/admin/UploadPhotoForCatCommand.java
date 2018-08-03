package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.impl.expert.EditCatCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.controller.util.MessageManager;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.EmailAlreadyExistsException;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;
import org.apache.http.protocol.RequestContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
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
            File path = new File(ConfigurationManager.getProperty("path.photo.cat"));

            if (!path.exists()) {
                path.mkdir();
            }

            Part filePart = request.getPart("file");
            String suffix = getFileSuffix(filePart);
            File file = File.createTempFile("cat-up", suffix, path);

            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            catService.addCatPhoto(catId, file.getName());

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page")); //Обратно в кабинет

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Something pretty bad has happened: ", e);
        }
    }


    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        logger.log(Level.DEBUG, "Part Header = {0}", partHeader);

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }


    private String getFileSuffix(final Part part) throws ServiceException {
        String fileName = getFileName(part);

        if (fileName != null) {
            return fileName.substring(fileName.lastIndexOf("."));
        } else {
            throw new ServiceException("Wrong file format");
        }
    }
}
