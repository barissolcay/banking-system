/**
 * Exception class for invalid amounts in banking operations.
 * This exception is thrown when an invalid amount is provided, such as a negative or zero value.
 */
public class InvalidAmountException extends Exception {

    /**
     * Constructor to initialize the InvalidAmountException.
     *
     * @param message The error message explaining the reason for the exception.
     */
    public InvalidAmountException(String message) {
        super(message);
    }
}
