package by.epam.cattery.service.exception;

public class NoSuchUserException extends ServiceException {
    private static final long serialVersionUID = 2778987292347896107L;

    public NoSuchUserException() {
    }

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUserException(Throwable cause) {
        super(cause);
    }
}
