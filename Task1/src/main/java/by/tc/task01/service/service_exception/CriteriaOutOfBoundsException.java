package by.tc.task01.service.service_exception;

public class CriteriaOutOfBoundsException extends ServiceException {
    public CriteriaOutOfBoundsException() {}

    public CriteriaOutOfBoundsException(String message) {
        super(message);
    }

    public CriteriaOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriteriaOutOfBoundsException(Throwable cause) {
        super(cause);
    }
}
