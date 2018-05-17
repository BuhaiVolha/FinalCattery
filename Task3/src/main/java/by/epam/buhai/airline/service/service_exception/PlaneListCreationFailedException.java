package by.epam.buhai.airline.service.service_exception;

public class PlaneListCreationFailedException extends ServiceException {
    public PlaneListCreationFailedException() {}

    public PlaneListCreationFailedException(String message) {
        super(message);
    }

    public PlaneListCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaneListCreationFailedException(Throwable cause) {
        super(cause);
    }
}
