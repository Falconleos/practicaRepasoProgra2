package exceptions;

public class InvalidDaysException extends RuntimeException {
    public InvalidDaysException(String message) {
        super(message);
    }
}
