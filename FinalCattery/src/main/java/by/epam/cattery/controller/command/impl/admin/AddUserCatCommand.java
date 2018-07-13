package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Gender;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AddUserCatCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cat cat = createCat(request);

        CatService catService = ServiceFactory.getInstance().getCatService();

        try {
            catService.addUserCat(cat, request.getAttribute("offerId").toString());
            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));

        } catch (ServiceException e) {
            System.out.println("smth bad happened " + e);
        }
    }


    private Cat createCat(HttpServletRequest request) {
        Cat cat = new Cat();
        HttpSession session = request.getSession();

        cat.setName(request.getParameter("name"));
        cat.setLastname(request.getParameter("lastname"));
        cat.setGender(Gender.valueOf(request.getParameter("gender")));
        cat.setAge(request.getParameter("age"));
        cat.setPrice(Double.parseDouble(request.getParameter("price")));  // controller exception?
        cat.setDescription(request.getParameter("description"));
        cat.setBodyColour(request.getParameter("bodyColour"));
        cat.setEyesColour(request.getParameter("eyesColour"));
        cat.setFemaleParent(request.getParameter("femaleParent"));
        cat.setMaleParent(request.getParameter("maleParent"));
        cat.setUserMadeOfferId(Integer.parseInt(session.getAttribute("userMadeOfferId").toString()));

//        if (session.getAttribute("userMadeOfferId") != null) {
//            cat.setUserMadeOfferId(Integer.parseInt(session.getAttribute("userMadeOfferId").toString()));
//        } else {
//            cat.setUserMadeOfferId(1);
//        }
//        session.removeAttribute("userMadeOfferId");

        return cat;
    }
}
