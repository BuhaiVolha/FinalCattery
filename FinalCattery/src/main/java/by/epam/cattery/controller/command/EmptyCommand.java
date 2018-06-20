package by.epam.cattery.controller.command;

import by.epam.cattery.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.login");
        request.getSession().invalidate(); //&?????????????????????????????????
        return page;
    }
}
