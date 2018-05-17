package by.tc.task01.dao.dao_exception;

public class GoodsCreationFailedException extends DAOException {
    public GoodsCreationFailedException() {}

    public GoodsCreationFailedException(String message) {
        super(message);
    }

    public GoodsCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoodsCreationFailedException(Throwable cause) {
        super(cause);
    }
}
