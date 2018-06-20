package by.epam.cattery.controller.command;

import by.epam.cattery.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;


public class LanguageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        String page = ConfigurationManager.getProperty(request.getContextPath() + "path.page.main");
        System.out.println(page);
        return page;
    }
}
