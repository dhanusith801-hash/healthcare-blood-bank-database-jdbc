# Healthcare Blood Bank Database JDBC

A Java-based **Healthcare Blood Bank Database Management System** built using **JDBC** and **MySQL**.  
This project helps manage **donors, recipients, blood stock, and blood issue records** in a blood bank system.

---

## 📌 Features

- Add new donor details
- View all donors
- Add new recipient details
- View all recipients
- Add or update blood stock
- View available blood stock
- Issue blood to recipients
- Automatically reduce stock after issuing blood
- Store all data in MySQL database

---

## 🛠️ Technologies Used

- **Java**
- **JDBC (Java Database Connectivity)**
- **MySQL**
- **MySQL Connector/J**
- **PowerShell / Command Prompt**

---

## 📂 Project Structure

```text
HealthcareBloodBankDB
│
├── database
│   └── bloodbank.sql
│
├── src
│   ├── DBConnection.java
│   ├── Donor.java
│   ├── Recipient.java
│   ├── BloodStock.java
│   ├── BloodBankDAO.java
│   └── Main.java
│
└── README.md

🗄️ Database Tables

This project uses the following MySQL tables:

donor
recipient
blood_stock
blood_issue
⚙️ Setup Instructions
1. Create Database

Run the SQL file:

database/bloodbank.sql

This creates:

bloodbankdb
donor
recipient
blood_stock

Also run the additional SQL for blood issue table:

USE bloodbankdb;

CREATE TABLE blood_issue (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    recipient_id INT NOT NULL,
    blood_group VARCHAR(5) NOT NULL,
    units_issued INT NOT NULL,
    issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (recipient_id) REFERENCES recipient(recipient_id)
);
2. Configure Database Connection

Open:

src/DBConnection.java

Update:

private static final String URL = "jdbc:mysql://localhost:3306/bloodbankdb";
private static final String USER = "root";
private static final String PASSWORD = "root";

Change the password according to your MySQL setup.

3. Compile the Project
javac -cp "lib/*" src/*.java
4. Run the Project
java -cp "lib/*;src" Main
📋 Sample Menu
===== Healthcare Blood Bank Database =====
1. Add Donor
2. View Donors
3. Add Recipient
4. View Recipients
5. Add/Update Blood Stock
6. View Blood Stock
7. Issue Blood
8. Exit
✅ Example Workflow
Add donor details
Add recipient details
Add blood stock for a blood group
Issue blood to a recipient using recipient ID
View updated blood stock after issuing
🎯 Project Objective

The main objective of this project is to develop a simple healthcare blood bank management system that demonstrates:

JDBC connectivity with MySQL
CRUD operations in Java
Database table relationships
Real-world healthcare use case implementation
🚀 Future Enhancements
Search donor by blood group
Search recipient by ID
View blood issue history
Delete/update donor and recipient records
GUI using Java Swing
Alerts for low blood stock
👨‍💻 Author

Dhanusith

📜 License

This project is created for educational and academic purposes.


### After pasting into `README.md`, run:
```bash
git add README.md
git commit -m "Added project README"
git push -u origin main
