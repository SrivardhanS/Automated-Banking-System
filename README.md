# 💳 Automated Banking System

An Automated Banking System implemented in **Java**, leveraging **JDBC** and **MySQL** for database management. This application allows users to perform essential banking operations such as account creation, deposits, withdrawals, and balance checks while maintaining a transaction history.

## ✨ Key Features:

- 🔐 **User Authentication**: Secure login and password verification.
- 💰 **Account Management**: Users can create accounts, check balances, make deposits, withdrawals, and transfer money.
- 🧾 **Transaction Logging**: All transactions (deposits, withdrawals) are logged for future reference.
- 🗃️ **Data Persistence**: Account information and transaction history are stored and managed in a **MySQL** database.

---

## ⚙️ **Technical Details**:

The system utilizes **JDBC** for interacting with the MySQL database and employs **Prepared Statements** for secure database operations, preventing SQL Injection attacks.

---

### 🛠️ **SQL Schema**: (`bankID` is the database name used)

#### **accounts**:

- `AccountID` → `PK`, `NN`, `AI` (Auto Increment)
- `UserID`
- `TypeID`
- `Balance`

#### **accountTypes**:

- `TypeID` → `PK`, `NN`, `AI`
- `TypeName` (Values: Saving or Current)

#### **transactions**:

- `TransactionID` → `PK`, `NN`, `AI`
- `AccountID`
- `TransactionType` (Values: Deposit or Withdraw)
- `Amount`
- `TransactionDate`

#### **users**:

- `UserID` → `PK`, `NN`, `AI`
- `Username` → `UQ` (Unique)
- `Password`

---

