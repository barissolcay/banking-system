public class SavingAccount extends Account {
    private double interestRate;
    private double minBalance;

    public SavingAccount(String accountID, double balance, double interestRate, double minBalance) {
        super(accountID, balance);
        this.interestRate = interestRate;
        this.minBalance = minBalance;
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }

        if (amount > balance + minBalance) {
            throw new InsufficientFundsException("Cannot withdraw this amount. Exceeds allowable limit.");
        }

        double newBalance = balance - amount;
        if (newBalance < minBalance) {
            double diff = minBalance - newBalance;
            double penalty = diff * 0.05;
            double total = amount + penalty;
            if (total > balance) {
                throw new InsufficientFundsException("Not enough funds after applying penalty.");
            }
            balance -= total;
        } else {
            balance -= amount;
        }
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    @Override
    public double computeInterest() {
        return balance * interestRate;
    }

    @Override
    public void printAccountInfoFormatted() {
        System.out.println("Account Info");
        System.out.println("Savings Account - Account Number: " + accountID);
        System.out.println("Balance: $" + balance);
        // interestRate örnekte yüzde olarak gösterilmiş (0.8 -> 80.0%)
        System.out.println("Interest Rate: " + (interestRate*100) + "%");
        System.out.println();
    }

    @Override
    public String evaluateAccountValue() {
        if (balance > 10000) {
            return "High Value Account";
        }
        return "Normal Value Account";
    }

    @Override
    public String evaluateRisk() {
        // Kullanıcının örnek çıktısına göre her şey stable, medium risk vs.
        // Biz spesifik olarak:
        // If balance < 120% minBalance => High Risk (orjinal kural)
        // ama kullanıcı hep stable diyor.
        // Örnekte SAV110 low risk stable.
        if (balance < 1.2*minBalance) {
            return "High Risk";
        }
        // Hiç medium risk örneği yok saving için, low risk yapalım.
        return "Low Risk";
    }

    @Override
    public String getTypeName() {
        return "Saving Account";
    }
}
