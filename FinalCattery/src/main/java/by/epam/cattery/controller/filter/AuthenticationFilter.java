package by.epam.cattery.controller.filter;

import by.epam.cattery.controller.util.ConfigurationManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = { "/jsp/user/*"})
public class AuthenticationFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //User user = (User) request.getSession(true).getAttribute("user");


        //if (user == null) {
        if (request.getSession(true).getAttribute("login") == null) {
            response.sendRedirect(request.getContextPath()
                    + ConfigurationManager.getProperty("path.page.main"));
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
