package by.epam.cattery.controller;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.CommandProvider;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ImageUploader",
        urlPatterns = "/imageUploader")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50) // 50MB ??
public class ImageUploader extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ImageUploader.class);
    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestContent requestContent = new RequestContent();
        requestContent.extractValues(request);
        ActionCommand command = CommandProvider.getInstance().defineCommand(requestContent);
        RequestResult requestResult = null;

        try {
            requestResult = command.execute(requestContent);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to upload photo", e);
            redirect(ERROR_PAGE, response);
        }
        requestContent.insertValues(request);

        redirect(request.getContextPath() + requestResult.getPage(), response);
    }


    private void redirect(String page, HttpServletResponse response) throws IOException {
        response.sendRedirect(page);
    }
}
