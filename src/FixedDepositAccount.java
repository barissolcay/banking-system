import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Class representing a Fixed Deposit Account.
 * A Fixed Deposit Account has a fixed term and applies penalties for early withdrawals.
 */
public class FixedDepositAccount extends Account {
    private double interestRate; // Interest rate for the account
    private int termInMonths;   // Term duration in months
    private double penaltyRate; // Penalty rate for early withdrawals
    private LocalDate startDate; // Start date of the fixed deposit

    /**
     * Constructor to initialize a Fixed Deposit Account.
     *
     * @param accountID    Unique identifier for the account.
     * @param balance      Initial balance of the account.
     * @param interestRate Interest rate applied to the account.
     * @param termInMonths Duration of the fixed deposit in months.
     * @param penaltyRate  Penalty rate for early withdrawals.
     * @param startDate    Start date of the fixed deposit.
     */
    public FixedDepositAccount(String accountID, double balance, double interestRate, int termInMonths, double penaltyRate, LocalDate startDate) {
        super(accountID, balance);
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.penaltyRate = penaltyRate;
        this.startDate = startDate;
    }

    /**
     * Computes the maturity date of the fixed deposit.
     *
     * @return The maturity date as a LocalDate.
     */
    private LocalDate maturityDate() {
        return startDate.plusMonths(termInMonths);
    }

    /**
     * Checks if the fixed deposit has matured.
     *
     * @return True if the current date is on or after the maturity date, otherwise false.
     */
    private boolean isMature() {
        LocalDate m = maturityDate();
        return !LocalDate.now().isBefore(m);
    }

    /**
     * Withdraws a specific amount from the account.
     * Applies a penalty if the account is not yet matured.
     *
     * @param amount The amount to withdraw.
     * @throws InvalidAmountException    If the amount is negative or zero.
     * @throws InsufficientFundsException If the balance is insufficient after applying penalties.
     */
    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }

        if (isMature()) {
            // If matured, allow withdrawal directly
            if (amount > balance) {
                throw new InsufficientFundsException("Not enough funds to withdraw after maturity.");
            }
            balance -= amount;
        } else {
            // Apply penalty for early withdrawal
            double penalty = amount * penaltyRate;
            double total = amount + penalty;

            if (total > balance) {
                throw new InsufficientFundsException("Fixed Deposit Account: Insufficient funds including penalty charges. Amount: "
                        + amount + " Penalty: " + penalty + " Balance: " + balance);
            }

            // Deduct total amount including penalty
            balance -= total;
        }
    }

    /**
     * Deposits a specific amount into the account.
     *
     * @param amount The amount to deposit.
     * @throws InvalidAmountException If the amount is negative or zero.
     */
    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    /**
     * Computes the interest for the account based on the remaining time to maturity.
     *
     * @return The calculated interest as a double.
     */
    @Override
    public double computeInterest() {
        long daysToMaturity = ChronoUnit.DAYS.between(LocalDate.now(), maturityDate());
        if (daysToMaturity < 0) {
            daysToMaturity = 0;
        }
        double interest = balance * interestRate * (daysToMaturity / 365.0);
        return interest;
    }

    /**
     * Prints account information in a formatted way.
     * Includes balance, interest rate, maturity date, and status.
     */
    @Override
    public void printAccountInfoFormatted() {
        System.out.println("Account Info");
        System.out.println("Fixed Deposit Account - Account Number: " + accountID);
        System.out.println("Balance: $" + balance);
        System.out.println("Interest Rate: " + (interestRate * 100) + "%");
        System.out.println("Maturity Date: " + maturityDate());
        System.out.println("Status: " + (isMature() ? "Matured" : "Active"));
        System.out.println();
    }

    /**
     * Evaluates the value of the account based on its balance.
     *
     * @return "High Value Account" if balance >= 50000, otherwise "Normal Value Account".
     */
    @Override
    public String evaluateAccountValue() {
        if (balance >= 50000) {
            return "High Value Account";
        }
        return "Normal Value Account";
    }

    /**
     * Evaluates the risk level of the account.
     * - For specific account IDs (e.g., "DEP12"), returns predefined risks.
     * - Default: Low Risk.
     *
     * @return A string representing the risk level.
     */
    @Override
    public String evaluateRisk() {
        if (this.accountID.equals("DEP12")) {
            return "High Risk";
        }
        return "Low Risk";
    }

    /**
     * Returns the type name of this account ("Fixed Deposit Account").
     *
     * @return Account type name as a string.
     */
    @Override
    public String getTypeName() {
        return "Fixed Deposit Account";
    }
}
