package by.epam.cattery.controller.command;

import by.epam.cattery.resource.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements ActionCommand {

//    public void execute(HttpServletRequest request, HttpServletResponse response) {
//    //public String execute(HttpServletRequest request, HttpServletResponse response) {
//        String page = ConfigurationManager.getProperty("path.page.main");
//        request.getSession().invalidate();
//
//        //return page;
//    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        session.removeAttribute("login");
        session.invalidate();

        response.sendRedirect(ConfigurationManager.getProperty("path.page.index"));
    }
}
