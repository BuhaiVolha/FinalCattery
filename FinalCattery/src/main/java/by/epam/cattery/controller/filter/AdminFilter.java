package by.epam.cattery.controller.filter;

import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/jsp/admin/*"})
public class AdminFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if (session.getAttribute("role") != Role.ADMIN) {

            if (session.getAttribute("login") == null) {
                session.setAttribute("errorLoginPassMessage", ConfigurationManager.getInstance()
                        .getMessage("message.accessdenied"));
            }
            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.main"));

        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
