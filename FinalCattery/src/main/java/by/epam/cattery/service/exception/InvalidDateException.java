package by.epam.cattery.service.exception;

public class InvalidDateException extends ServiceException {
    private static final long serialVersionUID = 156823268857907396L;

    public InvalidDateException() {
    }

    public InvalidDateException(String message) {
        super(message);
    }

    public InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateException(Throwable cause) {
        super(cause);
    }
}
