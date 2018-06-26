package by.epam.cattery.dao.connection;

public class ConnectionPoolException extends Exception {
    private static final long serialVersionUID = 6069133364046899507L;

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
