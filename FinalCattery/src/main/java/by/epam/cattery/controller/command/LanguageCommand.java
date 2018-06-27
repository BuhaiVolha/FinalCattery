package by.epam.cattery.controller.command;

import by.epam.cattery.resource.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        response.sendRedirect(ConfigurationManager.getProperty("path.page.main"));
    }
}



// don't mind it

//<li class="nav-item mx-0 mx-lg-1">
//<form action="/controller" method="get">
//<input type="hidden" name="command" value="Language" />
//<input type="hidden" name="local" value="ru" /> <input type="submit"
//        value="${ru_button}" /><br />
//</form>
//</li>
//<li class="nav-item mx-0 mx-lg-1">
//
//<form action="/controller" method="get">
//<input type="hidden" name="command" value="Language" />
//<input type="hidden" name="local" value="en" /> <input type="submit"
//        value="${en_button}" /><br />
//</form>
//</li> ???????????????


//<li class="nav-item mx-0 mx-lg-1">
//<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
//        href="/controller?command=language&from=${pageContext.request.requestURI}&local=en">${en_button}</a>
//</li>
//<li class="nav-item mx-0 mx-lg-1">
//<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
//        href="/controller?command=language&from=${pageContext.request.requestURI}&local=ru">${ru_button}</a>
//</li>
