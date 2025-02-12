# Banking System

Banking System is a project designed to manage various types of bank accounts and transactions. The system supports Current Accounts, Saving Accounts, and Fixed Deposit Accounts, providing functionalities such as deposits, withdrawals, interest computation, and risk evaluation.

## Project Overview

Banking System is developed using Java and provides a comprehensive system for managing bank accounts and processing transactions. The system reads account data and transaction records from files and performs various operations based on the provided data.

### Features

The project includes:
1. **Account Management**: Supports different types of accounts including Current, Saving, and Fixed Deposit accounts.
2. **Transaction Processing**: Processes transactions between accounts, ensuring sufficient funds and applying penalties if necessary.
3. **Interest Computation**: Computes interest for Saving and Fixed Deposit accounts based on their respective interest rates.
4. **Risk Evaluation**: Evaluates the risk level of each account based on its balance and overdraft usage.
5. **Command Line Interface**: Reads account and transaction data from files and displays results on the console.

## Technologies Used

- **Java**: The primary programming language used for developing the banking system.

## Project Structure

The project structure includes several directories and files organized as follows:

- **`src/BankingSystem.java`**: The main file that initializes the system and processes account data and transactions.
- **`src/Account.java`**: Abstract class representing the basic structure and behavior of all account types.
- **`src/CurrentAccount.java`**: Represents a Current Account with an overdraft facility.
- **`src/SavingAccount.java`**: Represents a Saving Account with an interest rate and minimum balance requirement.
- **`src/FixedDepositAccount.java`**: Represents a Fixed Deposit Account with a fixed term and penalties for early withdrawals.
- **`src/Transaction.java`**: Class representing a transaction between two accounts.
- **`src/TransactionRecord.java`**: Class representing a record of a transaction.

## Usage

### Prerequisites

To run the project, you need to have the following installed:
- **Java Development Kit (JDK)**: Ensure you have JDK installed on your system.

### Running the Project

1. Clone the repository:

    ```bash
    git clone https://github.com/barissolcay/banking-system.git
    cd banking-system
    ```

2. Compile the Java files:

    ```bash
    javac src/*.java
    ```

3. Run the Banking System with input files:

    ```bash
    java -cp src BankingSystem <accountsFile> <transactionsFile>
    ```

    Replace `<accountsFile>` and `<transactionsFile>` with the actual file paths.

## Contributing

We welcome contributions to improve the Banking System project. Feel free to open issues or submit pull requests if you have suggestions for improvements or find any bugs.

## License

MIT License

```markdown
MIT License

Copyright (c) 2025 Baris Solcay

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
