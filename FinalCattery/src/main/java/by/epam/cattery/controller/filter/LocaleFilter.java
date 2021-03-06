package by.epam.cattery.controller.filter;

import by.epam.cattery.controller.command.constant.SessionConst;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * The filter sets english as default locale.
 *
 */
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    public void destroy() {
    }

    /**
     *
     * Takes locale from session, if it's {@code null} sets it to english
     *
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionConst.LOCALE) == null) {
            session.setAttribute(SessionConst.LOCALE, SessionConst.DEFAULT_LOCALE);
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
