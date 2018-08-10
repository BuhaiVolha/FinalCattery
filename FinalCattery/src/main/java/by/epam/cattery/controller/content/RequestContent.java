package by.epam.cattery.controller.content;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.IOException;
import java.util.*;


public class RequestContent {
    private static final Logger logger = LogManager.getLogger(RequestContent.class);

    private static final String REFERER = "referer";
    private static final String MULTIPART = "multipart/form-data";

    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private Collection<Part> requestParts;
    private String page;
    private String referer;
    private boolean sessionToBeInvalidated;


    public RequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        page = null;
    }


    public void extractValues(HttpServletRequest request) {

        extractAttributes(request);
        extractParameters(request);
        extractSessionAttributes(request);
        extractParts(request);
        sessionToBeInvalidated = false;
        referer = request.getHeader(REFERER);
    }


    public void insertValues(HttpServletRequest request) {

        insertAttributes(request);
        insertSessionAttributes(request);
    }


    private void extractAttributes(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            requestAttributes.put(attributeName, request.getAttribute(attributeName));
        }
    }


    private void extractParameters(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            requestParameters.put(parameterName, request.getParameterValues(parameterName));
        }
    }


    private void extractSessionAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            logger.log(Level.DEBUG, "Session wasn't null");
            Enumeration<String> sessionAttributeNames = session.getAttributeNames();
            
            while (sessionAttributeNames.hasMoreElements()) {
                String sessionAttributeName = sessionAttributeNames.nextElement();
                sessionAttributes.put(sessionAttributeName, session.getAttribute(sessionAttributeName));
            }
        }
    }


    private void extractParts(HttpServletRequest request) {
        // ПРОВЕРИТЬ
        try {
            if (request.getContentType() != null && request.getContentType().toLowerCase().contains(MULTIPART)) {
                requestParts = request.getParts();
            }
        } catch (ServletException | IOException e) {
            logger.log(Level.ERROR, "Failed to get parts from request", e);
        }
    }


    private void insertAttributes(HttpServletRequest request) {

        for (Map.Entry<String, Object> entry : requestAttributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    private void insertSessionAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {

            logger.log(Level.DEBUG, "Session wasn't null (inserting)");
            for (Map.Entry<String, Object> entry : sessionAttributes.entrySet()) {
                session.setAttribute(entry.getKey(), entry.getValue());
            }
            if (sessionToBeInvalidated) {
                session.invalidate();
            }
        }
    }


    public void setAttribute(String name, Object value) {
        requestAttributes.put(name, value);
    }

    public void setSessionAttribute(String name, Object value) {
        sessionAttributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return requestAttributes.get(name);
    }

    public Object getSessionAttribute(String name) {
        return sessionAttributes.get(name);
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCurrentPage() {
        return referer;
    }

    public String[] getAllParameters(String name) {
        return requestParameters.get(name);
    }

    public String getParameter(String name) {
        String[] parameters = requestParameters.get(name);

        if (parameters != null) {
            return parameters[0];
        }
        return null;
    }

    public Part getPart(String name) {

        Part part = null;

        if (requestParts != null) {
            for (Part requestPart : requestParts) {

                if (requestPart.getName().equals(name)) {
                    part = requestPart;
                    break;
                }
            }
        }
        return part;
    }


    public boolean isSessionToBeInvalidated() {
        return sessionToBeInvalidated;
    }


    public void setSessionToBeInvalidated(boolean sessionToBeInvalidated) {
        this.sessionToBeInvalidated = sessionToBeInvalidated;
    }
}
