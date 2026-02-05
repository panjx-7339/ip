package penguin.exception;

/**
 * Represents a custom exception used by the Penguin application.
 * <p>
 * This exception is thrown when an application-specific error occurs,
 * such as invalid user input or an illegal operation on tasks.
 * </p>
 */
public class PenguinException extends Exception {
    public PenguinException(String message) {
        super(message);
    }
}
