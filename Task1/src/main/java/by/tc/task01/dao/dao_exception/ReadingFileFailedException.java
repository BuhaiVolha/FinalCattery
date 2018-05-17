package by.tc.task01.dao.dao_exception;

public class ReadingFileFailedException extends DAOException {
    public ReadingFileFailedException() {}

    public ReadingFileFailedException(String message) {
        super(message);
    }

    public ReadingFileFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadingFileFailedException(Throwable cause) {
        super(cause);
    }
}
