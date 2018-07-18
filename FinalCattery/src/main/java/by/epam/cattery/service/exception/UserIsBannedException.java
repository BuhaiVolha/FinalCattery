package by.epam.cattery.service.exception;

public class UserIsBannedException extends ServiceException {
    private static final long serialVersionUID = 5367379138882817091L;

    public UserIsBannedException() {
    }

    public UserIsBannedException(String message) {
        super(message);
    }

    public UserIsBannedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsBannedException(Throwable cause) {
        super(cause);
    }
}

