package by.epam.cattery.controller.command.constant;


public final class MessageConst {

    public static final String EMAIL_TAKEN = "emailTaken";
    public static final String LOGIN_ALREADY_EXISTS = "loginExists";
    public static final String INVALID_INPUT = "inputInvalid";
    public static final String WRONG_LOGIN_OR_PASSWORD = "loginError";
    public static final String USER_IS_BANNED = "userBanned";
    public static final String INVALID_BIRTH_DATE = "birthdayInvalid";
    public static final String ACCESS_DENIED = "accessDenied";


    private MessageConst() {
        throw new AssertionError();
    }
}
