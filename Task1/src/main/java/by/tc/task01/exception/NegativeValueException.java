package by.tc.task01.exception;

public class NegativeValueException extends ValidationFailedException {

    public NegativeValueException(String msg) {
        super(msg);
    }
}
