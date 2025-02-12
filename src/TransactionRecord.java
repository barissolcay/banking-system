import java.time.LocalDate;

/**
 * Class representing a record of a transaction.
 * A TransactionRecord stores details such as the transaction ID, date, sender, receiver, and amount.
 */
public class TransactionRecord {
    private String id;           // Unique identifier for the transaction
    private LocalDate date;      // Date of the transaction
    private String senderId;     // ID of the sender account
    private String receiverId;   // ID of the receiver account
    private double amount;       // Amount transferred in the transaction

    /**
     * Constructor to initialize a TransactionRecord.
     *
     * @param id         Unique identifier for the transaction.
     * @param date       Date of the transaction.
     * @param senderId   ID of the sender account.
     * @param receiverId ID of the receiver account.
     * @param amount     Amount transferred in the transaction.
     */
    public TransactionRecord(String id, LocalDate date, String senderId, String receiverId, double amount) {
        this.id = id;
        this.date = date;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
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
     * @return Transaction date as a LocalDate object.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the sender account ID.
     *
     * @return Sender account ID as a String.
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Returns the receiver account ID.
     *
     * @return Receiver account ID as a String.
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * Returns the amount transferred in the transaction.
     *
     * @return Transaction amount as a double.
     */
    public double getAmount() {
        return amount;
    }
}
