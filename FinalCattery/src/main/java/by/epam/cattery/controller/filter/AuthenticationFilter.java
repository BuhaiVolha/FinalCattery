package by.epam.cattery.controller.filter;

import by.epam.cattery.controller.command.constant.MessageConst;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.util.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = { "/jsp/authorized/*"})
public class AuthenticationFilter implements Filter {
    private static final String MAIN_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.MAIN_PAGE);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionConst.LOGIN) == null) {
            session.setAttribute(SessionConst.LOG_IN_FAIL, MessageConst.ACCESS_DENIED);

            response.sendRedirect(MAIN_PAGE);

        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
