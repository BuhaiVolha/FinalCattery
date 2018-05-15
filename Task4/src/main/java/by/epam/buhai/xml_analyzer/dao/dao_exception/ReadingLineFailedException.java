package by.epam.buhai.xml_analyzer.dao.dao_exception;

public class ReadingLineFailedException extends DAOException {
    public ReadingLineFailedException() {}

    public ReadingLineFailedException(String message) {
        super(message);
    }

    public ReadingLineFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadingLineFailedException(Throwable cause) {
        super(cause);
    }
}
