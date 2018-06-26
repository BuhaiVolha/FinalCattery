package by.epam.cattery.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = -1292173682527846670L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
