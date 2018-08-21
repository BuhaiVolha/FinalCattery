package by.epam.cattery.controller.filter;

import by.epam.cattery.controller.command.constant.MessageConst;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.SessionConst;

import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The filter checks if the user has access to admin's pages.
 *
 */
@WebFilter(urlPatterns = { "/jsp/admin/*"})
public class AdminFilter implements Filter {
    private static final String MAIN_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.MAIN_PAGE);

    public void destroy() {
    }

    /**
     *
     * Takes user's role from session, if it's not {@code ADMIN} redirects to login page with message shown
     *
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionConst.ROLE) != Role.ADMIN) {

            if (session.getAttribute(SessionConst.LOGIN) == null) {
                session.setAttribute(SessionConst.LOG_IN_FAIL, MessageConst.ACCESS_DENIED);
            }
            response.sendRedirect(MAIN_PAGE);

        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
