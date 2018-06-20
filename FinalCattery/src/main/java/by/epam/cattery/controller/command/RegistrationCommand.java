package by.epam.cattery.controller.command;

import by.epam.cattery.entity.User;
import by.epam.cattery.resource.ConfigurationManager;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page = null;

        User user = createUser(request);
        HttpSession session = request.getSession();

        try {

            UserService userService = ServiceFactory.getInstance().getUserService();
            int userId = userService.register(user);

            session.setAttribute("userId", userId);
            session.setAttribute("login", user.getUserLogin());

            request.setAttribute("user", user.getUserLogin());
            page = ConfigurationManager.getProperty("path.page.successful-reg");

        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return page;
    }


    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setUserLogin(request.getParameter("login"));
        user.setUserPass(request.getParameter("password"));

        return user;
    }
}
