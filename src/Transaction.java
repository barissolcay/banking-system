import java.time.LocalDate;
import java.util.UUID;

/**
 * Class representing a Transaction between two accounts.
 * Each transaction has a unique ID, sender, receiver, amount, and date.
 */
public class Transaction {
    private String id;         // Unique transaction ID
    private Account sender;    // Sender account
    private Account receiver;  // Receiver account
    private double amount;     // Transaction amount
    private LocalDate date;    // Transaction date

    /**
     * Constructor to initialize a Transaction.
     *
     * @param sender   The account sending the amount.
     * @param receiver The account receiving the amount.
     * @param amount   The amount to be transferred.
     * @param date     The date of the transaction.
     */
    public Transaction(Account sender, Account receiver, double amount, LocalDate date) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Processes the transaction.
     * Withdraws the amount from the sender and deposits it into the receiver account.
     *
     * @throws InvalidAmountException    If the amount is invalid.
     * @throws InsufficientFundsException If the sender has insufficient funds.
     */
    public void process() throws InvalidAmountException, InsufficientFundsException {
        sender.withdraw(amount);
        receiver.deposit(amount);
    }

    /**
     * Returns the unique ID of the transaction.
     *
     * @return Transaction ID as a String.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the date of the transaction.
     *
     * @return Transaction date as a LocalDate.
     */
    public LocalDate getDate() {
        return date;
    }
}
