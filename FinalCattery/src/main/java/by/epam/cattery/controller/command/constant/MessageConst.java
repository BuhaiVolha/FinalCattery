package by.epam.cattery.controller.command.constant;

import by.epam.cattery.util.ConfigurationManager;

public final class MessageConst {
    public static final String EMAIL_TAKEN = "message.emailtaken";
    public static final String LOGIN_ALREADY_EXISTS = "message.loginexists";



    private MessageConst() {
        throw new AssertionError();
    }
}
