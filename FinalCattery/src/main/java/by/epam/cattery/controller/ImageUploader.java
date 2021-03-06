package by.epam.cattery.controller;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.CommandProvider;
import by.epam.cattery.controller.command.constant.PathConst;
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


/**
 * Controller for dealing with images uploading.
 *
 */
@WebServlet(name = "ImageUploader",
        urlPatterns = "/imageUploader")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50) // 50MB
public class ImageUploader extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ImageUploader.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.ERROR_PAGE);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    /**
     * Gets the type of command for processing request and executes this command.
     *
     * @param request an {@link HttpServletRequest} object that contains the client's request
     *
     * @param response an {@link HttpServletResponse} object that contains the response that
     *                 the servlet sends to the client
     *
     * @throws IOException if IOException has happened during {@code sendRedirect()} or
     *             {@code forward()}
     *
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestContent requestContent = new RequestContent();
        requestContent.extractValues(request);
        ActionCommand command = CommandProvider.getInstance().defineCommand(requestContent);
        RequestResult requestResult = null;

        try {
            requestResult = command.execute(requestContent);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to upload photo", e);
        }
        requestContent.insertValues(request);

        String path = requestResult != null ? requestResult.getPage() : ERROR_PAGE;
        redirect(path, response);
    }


    private void redirect(String page, HttpServletResponse response) throws IOException {
        response.sendRedirect(page);
    }
}
