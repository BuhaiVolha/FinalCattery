package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowAllCatsCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Cat> cats = null;
        HttpSession session = request.getSession();

        try {
            CatService catService = ServiceFactory.getInstance().getCatService();
            cats = catService.showAllCats();

        } catch (ServiceException e) {
            //redirect
            System.out.println(e);
            System.out.println("cats aren't here");
        }
        request.setAttribute("cats", cats);
        request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.cats")).forward(request, response);
    }
}
