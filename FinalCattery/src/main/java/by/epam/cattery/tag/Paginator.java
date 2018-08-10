package by.epam.cattery.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.Writer;

public class Paginator extends SimpleTagSupport {



    private String uri;
    private int currPage;
    private int totalPages;
    private int maxLinks;

    private Writer getWriter() {
        return getJspContext().getOut();
    }


    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();
        boolean lastPage = currPage == totalPages;
        int startPage = Math.max(currPage - maxLinks / 2, 1);
        int endPage = startPage + maxLinks;

        if (endPage > totalPages + 1) {
            int diff = endPage - totalPages;
            startPage -= diff - 1;

            if (startPage < 1) {
                startPage = 1;
            }
            endPage = totalPages + 1;
        }

        try {
            out.write("<ul class=\"paginatorList\">");

            if (currPage > 1) {
                out.write(constructLink(currPage - 1, "<", "paginatorPrev"));
            }
            for (int i = startPage; i < endPage; i++) {
                if (i == currPage)
                    out.write("<li class=\"paginatorCurr active"+ (lastPage && i == totalPages ? " paginatorLast" : "")  +"\">"+ currPage + "</li>");
                else
                    out.write(constructLink(i));
            }

            if (!lastPage) {
                out.write(constructLink(currPage + 1, ">", "paginatorNext paginatorLast"));
            }
            out.write("</ul>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }


    private String constructLink(int page) {
        return constructLink(page, String.valueOf(page), null);
    }

    private String constructLink(int page, String text, String className) {
        StringBuilder link = new StringBuilder("<li");

        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        link.append(">")
                .append("<a href=\"")
                .append(uri.replace("##", String.valueOf(page)))
                .append("\">")
                .append(text)
                .append("</a></li>");
        return link.toString();
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setMaxLinks(int maxLinks) {
        this.maxLinks = maxLinks;
    }
}
