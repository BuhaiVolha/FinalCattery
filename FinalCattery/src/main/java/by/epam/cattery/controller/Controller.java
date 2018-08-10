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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller",
        urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestContent requestContent = new RequestContent();
        requestContent.extractValues(request);
        ActionCommand command = CommandProvider.getInstance().defineCommand(requestContent);
        RequestResult requestResult = null;

        try {
            requestResult = command.execute(requestContent);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to process command", e);
            redirect(ERROR_PAGE, response);
        }
        requestContent.insertValues(request);

        if (requestResult != null) {
            switch (requestResult.getNavigationType()) {
                case FORWARD:
                    forward(requestResult.getPage(), request, response);
                    break;
                case REDIRECT:
                    redirect(requestResult.getPage(), response);
                    break;
            }
        } else {
            logger.log(Level.ERROR, "Null request result");
            redirect(ERROR_PAGE, response);
        }
    }


    private void forward(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }

    private void redirect(String page, HttpServletResponse response) throws IOException {
        response.sendRedirect(page);
    }
}
