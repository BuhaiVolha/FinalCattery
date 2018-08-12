package by.epam.cattery.controller.command.constant;


public final class MessageConst {
    public static final String EMAIL_TAKEN = "message.emailtaken";
    public static final String LOGIN_ALREADY_EXISTS = "message.loginexists";
    public static final String INVALID_INPUT = "message.inputinvalid";
    public static final String WRONG_LOGIN_OR_PASSWORD = "message.loginerror";
    public static final String USER_IS_BANNED = "message.userbanned";



    private MessageConst() {
        throw new AssertionError();
    }
}
