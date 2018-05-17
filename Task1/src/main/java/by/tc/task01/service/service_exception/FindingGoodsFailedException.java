package by.tc.task01.service.service_exception;

public class FindingGoodsFailedException extends ServiceException {
    public FindingGoodsFailedException() {}

    public FindingGoodsFailedException(String message) {
        super(message);
    }
    public FindingGoodsFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindingGoodsFailedException(Throwable cause) {
        super(cause);
    }
}
