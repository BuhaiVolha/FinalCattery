package by.epam.cattery.controller.command.constant;

public final class SessionConst {
    public final static String ID = "userId";
    public final static String LOGIN = "login";
    public final static String ROLE = "role";
    public final static String LOCALE = "local";

    public final static String LOG_IN_FAIL = "errorLoginPassMessage";

    private SessionConst() {
        throw new AssertionError();
    }
}
