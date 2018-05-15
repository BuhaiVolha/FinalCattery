package by.epam.buhai.xml_analyzer.service.service_exception;

public class FindingNodeFailedException extends ServiceException {
    public FindingNodeFailedException() {}

    public FindingNodeFailedException(String message) {
        super(message);
    }
    public FindingNodeFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindingNodeFailedException(Throwable cause) {
        super(cause);
    }
}
