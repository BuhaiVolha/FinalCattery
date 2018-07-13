package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("local", request.getParameter("local"));

        String viewPath = request.getHeader("referer");
        response.sendRedirect(viewPath);
    }
}