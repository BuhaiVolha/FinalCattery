package by.epam.buhai.xml_analyzer.exception;

public class NodeCreationFailedException extends Task4Exception {
    public NodeCreationFailedException() {}
    public NodeCreationFailedException(String message) {
        super(message);
    }
    public NodeCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
