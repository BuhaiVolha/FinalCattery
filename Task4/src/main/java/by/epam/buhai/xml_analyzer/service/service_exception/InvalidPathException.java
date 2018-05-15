package by.epam.buhai.xml_analyzer.service.service_exception;

public class InvalidPathException extends ServiceException {
    public InvalidPathException() {}

    public InvalidPathException(String message) {
        super(message);
    }

    public InvalidPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPathException(Throwable cause) {
        super(cause);
    }
}
