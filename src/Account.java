import java.time.LocalDate;

/**
 * Abstract class representing a bank account.
 * This class defines the basic structure and behavior for all account types.
 */
public abstract class Account {
    protected String accountID; // Unique identifier for the account
    protected double balance;   // Current balance of the account

    /**
     * Constructor to initialize an account.
     *
     * @param accountID Unique identifier for the account.
     * @param balance   Initial balance of the account.
     */
    public Account(String accountID, double balance) {
        this.accountID = accountID;
        this.balance = balance;
    }

    /**
     * Returns the account ID.
     *
     * @return Account ID as a String.
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return Current balance as a double.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Abstract method to withdraw a specific amount from the account.
     *
     * @param amount The amount to withdraw.
     * @throws InvalidAmountException    If the amount is negative or invalid.
     * @throws InsufficientFundsException If the account has insufficient balance.
     */
    public abstract void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException;

    /**
     * Abstract method to deposit a specific amount into the account.
     *
     * @param amount The amount to deposit.
     * @throws InvalidAmountException If the amount is negative or invalid.
     */
    public abstract void deposit(double amount) throws InvalidAmountException;

    /**
     * Abstract method to calculate the interest for the account.
     *
     * @return Calculated interest as a double.
     */
    public abstract double computeInterest();

    /**
     * Abstract method to print account details in a formatted way.
     */
    public abstract void printAccountInfoFormatted();

    /**
     * Abstract method to evaluate the value of the account.
     *
     * @return A string representing the account's value evaluation.
     */
    public abstract String evaluateAccountValue();

    /**
     * Abstract method to evaluate the risk level of the account.
     *
     * @return A string representing the account's risk level.
     */
    public abstract String evaluateRisk();

    /**
     * Generates a user-friendly risk evaluation string.
     * This method is tailored to match specific output requirements.
     *
     * @return A formatted risk evaluation string for user output.
     */
    public String evaluateRiskForOutput() {
        String risk = evaluateRisk();
        String type = getTypeName();

        // Standardized messages based on the risk level
        if ("Low Risk".equals(risk)) {
            return type + "-Low Risk: Account is in stable.";
        } else if ("Medium Risk".equals(risk)) {
            return type + "-Medium Risk: Account is in overdraft.";
        } else if ("High Risk".equals(risk)) {
            return type + "-High Risk: Overdraft usage close to the limit.";
        }
        return type + "-Low Risk: Account is in stable.";
    }

    /**
     * Abstract method to return the type name of the account.
     *
     * @return A string representing the account type.
     */
    public abstract String getTypeName();
}
