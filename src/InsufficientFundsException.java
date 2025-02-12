/**
 * Exception class for insufficient funds in banking operations.
 * This exception is thrown when an account does not have enough balance to complete a transaction.
 */
public class InsufficientFundsException extends Exception {

    /**
     * Constructor to initialize the InsufficientFundsException.
     *
     * @param message The error message explaining the reason for the exception.
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
