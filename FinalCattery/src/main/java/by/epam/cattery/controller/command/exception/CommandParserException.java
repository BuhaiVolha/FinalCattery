package by.epam.cattery.controller.command.exception;

public class CommandParserException extends Exception {
    private static final long serialVersionUID = -8198167066527649917L;

    public CommandParserException() {
    }

    public CommandParserException(String message) {
        super(message);
    }

    public CommandParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandParserException(Throwable cause) {
        super(cause);
    }
}
