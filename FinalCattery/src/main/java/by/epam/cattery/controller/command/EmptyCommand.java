package by.epam.cattery.controller.command;

import by.epam.cattery.resource.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmptyCommand implements ActionCommand {
    //public String execute(HttpServletRequest request, HttpServletResponse response) {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String page = ConfigurationManager.getProperty("path.page.login");
        request.getSession().invalidate(); //&?????????????????????????????????
        //return page;
    }
}
