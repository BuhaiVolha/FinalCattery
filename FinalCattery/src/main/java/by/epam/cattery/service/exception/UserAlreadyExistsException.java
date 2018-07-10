package by.epam.cattery.service.exception;

//иерархия эксепшенов?
public class UserAlreadyExistsException extends ServiceException {
    private static final long serialVersionUID = 5367379138882817091L;

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
