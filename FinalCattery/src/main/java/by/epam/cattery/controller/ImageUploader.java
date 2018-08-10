package by.epam.cattery.controller;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.CommandProvider;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import javax.servlet.ServletException;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestContent requestContent = new RequestContent();
        requestContent.extractValues(request);
        ActionCommand command = CommandProvider.getInstance().defineCommand(requestContent);
        RequestResult requestResult = null;
        try {
            requestResult = command.execute(requestContent);
        } catch (by.epam.cattery.service.exception.ServiceException e) {
            e.printStackTrace();
        }
        requestContent.insertValues(request);

        response.sendRedirect(request.getContextPath() + requestResult.getPage());
    }
}
