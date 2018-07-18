package by.epam.cattery.controller.filter;

import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.controller.util.MessageManager;
import by.epam.cattery.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/jsp/user/*"})
public class UserFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if (session.getAttribute("role") != Role.USER) {

            if (session.getAttribute("login") == null) {
                session.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.accessdenied"));
            }
            response.sendRedirect(ConfigurationManager.getProperty("path.page.main"));

        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}