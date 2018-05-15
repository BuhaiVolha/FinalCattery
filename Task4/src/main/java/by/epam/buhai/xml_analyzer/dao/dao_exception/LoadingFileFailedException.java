package by.epam.buhai.xml_analyzer.dao.dao_exception;

public class LoadingFileFailedException extends DAOException {
    public LoadingFileFailedException() {}

    public LoadingFileFailedException(String message) {
        super(message);
    }

    public LoadingFileFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadingFileFailedException(Throwable cause) {
        super(cause);
    }
}
