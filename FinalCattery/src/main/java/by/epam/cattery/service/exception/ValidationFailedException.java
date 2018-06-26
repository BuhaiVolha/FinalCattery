package by.epam.cattery.service.exception;

public class ValidationFailedException extends Exception {
    private static final long serialVersionUID = -187123426985795281L;

    public ValidationFailedException() {
    }

    public ValidationFailedException(String message) {
        super(message);
    }

    public ValidationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationFailedException(Throwable cause) {
        super(cause);
    }
}
