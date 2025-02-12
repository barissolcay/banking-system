/**
 * Represents a Current Account with an overdraft facility.
 * A Current Account allows withdrawal beyond the balance up to an overdraft limit.
 */
public class CurrentAccount extends Account {
    private double overdraftLimit; // Maximum amount allowed for overdraft

    /**
     * Constructor to initialize a Current Account.
     *
     * @param accountID     Unique identifier for the account.
     * @param balance       Initial balance of the account.
     * @param overdraftLimit The maximum overdraft limit for the account.
     */
    public CurrentAccount(String accountID, double balance, double overdraftLimit) {
        super(accountID, balance);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Returns the overdraft limit for the account.
     *
     * @return Overdraft limit as a double.
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Withdraws a specified amount from the account.
     * Ensures the amount does not exceed the balance and overdraft limit.
     *
     * @param amount The amount to withdraw.
     * @throws InvalidAmountException    If the withdrawal amount is not positive.
     * @throws InsufficientFundsException If the overdraft limit is exceeded.
     */
    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }

        double maxWithdraw = balance + overdraftLimit;
        if (amount > maxWithdraw) {
            throw new InsufficientFundsException("Overdraft limit exceeded.");
        }

        balance -= amount;
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount The amount to deposit.
     * @throws InvalidAmountException If the deposit amount is not positive.
     */
    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    /**
     * Computes the interest for the account.
     * Current accounts do not earn interest, so this method returns 0.
     *
     * @return 0.0 as the interest.
     */
    @Override
    public double computeInterest() {
        return 0.0;
    }

    /**
     * Prints account information in a formatted way.
     * Displays account type, account ID, balance, and overdraft limit.
     */
    @Override
    public void printAccountInfoFormatted() {
        System.out.println("Account Info");
        System.out.println("Current Account - Account Number: " + accountID);
        System.out.println("Balance: $" + balance);
        System.out.println("Overdraft Limit: $" + overdraftLimit);
        System.out.println();
    }

    /**
     * Evaluates the value of the account based on balance and overdraft limit.
     *
     * @return "High Value Account" if balance > 5000 and overdraft limit > 1000,
     *         otherwise "Normal Value Account".
     */
    @Override
    public String evaluateAccountValue() {
        if (balance > 5000 && overdraftLimit > 1000) {
            return "High Value Account";
        }
        return "Normal Value Account";
    }

    /**
     * Evaluates the risk level of the account based on balance and overdraft usage.
     *
     * @return "Low Risk" if balance >= 0,
     *         "Medium Risk" if overdraft usage is less than 80%,
     *         "High Risk" if overdraft usage is greater than 80%.
     */
    @Override
    public String evaluateRisk() {
        if (balance >= 0) {
            return "Low Risk";
        } else {
            double usage = Math.abs(balance) / overdraftLimit;
            if (usage > 0.8) {
                return "High Risk";
            } else {
                return "Medium Risk";
            }
        }
    }

    /**
     * Returns the type name of the account.
     *
     * @return "Current Account" as the account type.
     */
    @Override
    public String getTypeName() {
        return "Current Account";
    }
}
