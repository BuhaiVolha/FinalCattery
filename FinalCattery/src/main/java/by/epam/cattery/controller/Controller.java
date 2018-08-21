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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The front controller.
 *
 */
@WebServlet(name = "Controller",
        urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.ERROR_PAGE);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    /**
     * Extracts values from {@link HttpServletRequest} into {@link RequestContent}
     * Gets the type of command for processing request and executes this command.
     * After that inserts received values from {@link HttpServletRequest} into {@link RequestContent}
     * And perform {@code sendRedirect()} or {@code forward()} according to {@link by.epam.cattery.controller.content.NavigationType}
     * If {@code requestContent} was null or {@link ServiceException} has happened, redirects to the error page
     *
     * @param request an {@link HttpServletRequest} object that contains the client's request the
     *
     * @param response an {@link HttpServletResponse} object that contains the response that
     *            the servlet sends to the client
     * @throws ServletException if ServletException has happened during {@code forward()}
     *
     * @throws IOException if IOException has happened during {@code sendRedirect()} or
     *             {@code forward()}
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestContent requestContent = new RequestContent();
        requestContent.extractValues(request);
        ActionCommand command = CommandProvider.getInstance().defineCommand(requestContent);
        RequestResult requestResult = null;

        try {
            requestResult = command.execute(requestContent);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to process command", e);
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
            logger.log(Level.ERROR, "Some troubles occurred while executing command, command might not have been found");
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
