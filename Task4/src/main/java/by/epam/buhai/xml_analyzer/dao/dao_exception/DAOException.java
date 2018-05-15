package by.epam.buhai.xml_analyzer.dao.dao_exception;

public class DAOException extends Exception {
    public DAOException() {}

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
