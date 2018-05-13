package by.epam.buhai.xml_parser.exception;

public class LoadingFileFailedException extends Task4Exception {
    public LoadingFileFailedException() {}
    public LoadingFileFailedException(String message) {
        super(message);
    }
    public LoadingFileFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
