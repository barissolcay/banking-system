/**
 * Main class for the Banking System application.
 * This program processes account data and transactions from files provided as command-line arguments.
 * It evaluates account risk and generates transaction summaries for each account.
 */
public class BankingSystem {

    /**
     * Main method to start the Banking System.
     *
     * @param args Command-line arguments:
     *             args[0] should be the path to the accounts file.
     *             args[1] should be the path to the transactions file.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide accounts file and transactions file as arguments.");
            System.exit(1);
        }

        // Variables to store file paths and account data
        String accountsFile = args[0];
        String transactionsFile = args[1];

        Map<String, Account> accounts = new HashMap<>();
        List<TransactionRecord> transactionRecords = new ArrayList<>();

        /**
         * Reads account details from the file and initializes accounts.
         * Supported account types: Current, Saving, and Fixed Deposit accounts.
         */
        try (BufferedReader br = new BufferedReader(new FileReader(accountsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                String accID = parts[0].trim();
                String accType = parts[1].trim();
                switch (accType) {
                    case "Current":
                        // Parse Current account details
                        double cBalance = Double.parseDouble(parts[2].trim());
                        double cOverdraft = Double.parseDouble(parts[3].trim());
                        accounts.put(accID, new CurrentAccount(accID, cBalance, cOverdraft));
                        break;
                    case "Saving":
                        // Parse Saving account details
                        double sBalance = Double.parseDouble(parts[2].trim());
                        double sInterest = Double.parseDouble(parts[3].trim());
                        double sMinBalance = Double.parseDouble(parts[4].trim());
                        accounts.put(accID, new SavingAccount(accID, sBalance, sInterest, sMinBalance));
                        break;
                    case "Deposit":
                        // Parse Fixed Deposit account details
                        double dBalance = Double.parseDouble(parts[2].trim());
                        double dInterestRate = Double.parseDouble(parts[3].trim());
                        int dTerm = Integer.parseInt(parts[4].trim());
                        double dPenalty = Double.parseDouble(parts[5].trim());
                        LocalDate dStart = LocalDate.parse(parts[6].trim());
                        accounts.put(accID, new FixedDepositAccount(accID, dBalance, dInterestRate, dTerm, dPenalty, dStart));
                        break;
                    default:
                        System.err.println("Unknown account type: " + accType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        /**
         * Reads transaction records from the file.
         * Each transaction includes a sender ID, amount, and receiver ID.
         */
        List<String> transactionLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(transactionsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                transactionLines.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        /**
         * Processes each transaction and records valid ones.
         * Skips invalid transactions where sender or receiver does not exist.
         */
        for (String tline : transactionLines) {
            String[] parts = tline.split(",");
            String senderID = parts[0].trim();
            double amount = Double.parseDouble(parts[1].trim());
            String receiverID = parts[2].trim();

            Account sender = accounts.get(senderID);
            Account receiver = accounts.get(receiverID);

            if (sender == null || receiver == null) {
                // Invalid account, skip transaction
                continue;
            }

            Transaction tx = new Transaction(sender, receiver, amount, LocalDate.now());
            try {
                tx.process();
                transactionRecords.add(new TransactionRecord(tx.getId(), tx.getDate(), senderID, receiverID, amount));
            } catch (InvalidAmountException | InsufficientFundsException ex) {
                if (sender instanceof CurrentAccount && ex.getMessage().contains("Overdraft")) {
                    // Custom error message for overdraft issues
                    CurrentAccount cacc = (CurrentAccount) sender;
                    System.out.println("Current Account: Amount exceeds overdraft limit. Amount: " + amount
                            + " Balance: " + cacc.getBalance()
                            + " Limit: " + cacc.getOverdraftLimit());
                } else {
                    System.err.println(ex.getMessage());
                }
            }
        }

        /**
         * Generates account summaries and risk evaluations.
         * Summaries include transaction history and account risk status.
         */
        for (Account acc : accounts.values()) {
            System.out.println("****************** Summary for Account " + acc.getAccountID() + " ******************");

            List<TransactionRecord> related = transactionRecords.stream()
                    .filter(r -> r.getSenderId().equals(acc.getAccountID()) || r.getReceiverId().equals(acc.getAccountID()))
                    .collect(Collectors.toList());

            // Prints transaction history for the account
            for (TransactionRecord r : related) {
                System.out.println("------------------------------------");
                System.out.println("Transaction UD: " + r.getId());
                System.out.println("Sender: " + r.getSenderId());
                System.out.println("Receiver: " + r.getReceiverId());
                double sign = r.getSenderId().equals(acc.getAccountID()) ? -1.0 : 1.0;
                System.out.println("Amount: " + (sign * r.getAmount()));
                System.out.println("------------------------------------\n");
            }

            // Prints account details and risk evaluation
            acc.printAccountInfoFormatted();
            String riskStr = acc.evaluateRiskForOutput();

            System.out.println("Account Risk Evaluation");
            System.out.println(riskStr);
            System.out.println("************************************************************************************************************\n");
        }

    }

}
