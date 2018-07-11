package by.epam.cattery.controller;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.CommandProvider;
import by.epam.cattery.controller.util.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ActionFactory client = new ActionFactory();
        //ActionCommand command = client.defineCommand(request);
        ActionCommand command = CommandProvider.defineCommand(request);

        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }
        //page = command.execute(request, response);

//        if (page != null) {
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//            dispatcher.forward(request, response);
//
//        } else {
//            //page = ConfigurationManager.getProperty("path.page.index");
//            page = ConfigurationManager.getProperty("path.page.main");
//            request.getSession().setAttribute("nullpage", MessageManager.getProperty("message.nullpage"));
//            response.sendRedirect(request.getContextPath() + page);
//        }
    }
}