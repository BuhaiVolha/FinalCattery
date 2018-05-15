package by.epam.buhai.xml_analyzer.dao.dao_exception;

public class NodeCreationFailedException extends DAOException {
    public NodeCreationFailedException() {}

    public NodeCreationFailedException(String message) {
        super(message);
    }

    public NodeCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeCreationFailedException(Throwable cause) {
        super(cause);
    }
}
