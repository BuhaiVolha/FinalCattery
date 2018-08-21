package by.epam.cattery.controller.command.constant;

/**
 * Stores the names of session attributes.
 *
 */
public final class SessionConst {
    public final static String DEFAULT_LOCALE = "en";
    public final static String ID = "userId";
    public final static String LOGIN = "login";
    public final static String ROLE = "role";
    public final static String LOCALE = "local";
    public final static String LOG_IN_FAIL = "errorLoginPassMessage";

    private SessionConst() {
        throw new AssertionError();
    }
}
