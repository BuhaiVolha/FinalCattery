package by.epam.cattery.dao.exception;

public class DAOException extends Exception {
    private static final long serialVersionUID = -7909726096121739068L;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
