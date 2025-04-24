### 🧾 Employee Management System (EMS) – Java Console Application

### 📌 Overview

This Employee Management System is a Java-based console application designed to streamline and automate essential HR operations. It features 20 core functionalities and offers a robust platform for managing employee records, tracking attendance, generating payslips, calculating experience, and much more.

The application uses basic Java I/O operations and stores employee data in individual text files. It is an excellent example for beginners and intermediate learners to understand core Java programming, file handling, object-oriented design, and command-line interface development.

---

### ✨ Features

- 🔐 **Admin Login System** (Default: `admin` / `1234`)
- ➕ Add New Employee
- 🔍 View Employee Details
- ❌ Remove Employee
- ✏️ Update Employee Records (Salary, Name, Position, etc.)
- 📜 List All Employees
- 🔎 Search by Employee Name
- 🔢 Count Total Employees
- 💰 Filter Employees by Minimum Salary
- 🗃️ Backup All Employee Files
- 📊 Sort Employees by Salary
- 💼 Show Highest Paid Employee
- 📈 Display Average Salary
- 📘 Update Progress Report
- ⏱️ Update Working Hours
- 🧾 Generate Monthly Payslip
- 🕒 Mark Daily Attendance
- 📅 View Attendance History
- 📆 Calculate Total Work Experience
- 📅 Add Joining Date

---

### 🧠 Concepts Used

- Java I/O (`File`, `FileWriter`, `Scanner`)
- OOP (Encapsulation, Modularity)
- Exception Handling
- Date & Time API (`LocalDate`, `Period`)
- Menu-driven CLI interface
- Basic File System navigation

---

### 🗂️ Folder Structure

```
├── EMS.java                      # Main entry point with menu
├── Employee_Add.java            # Add employee logic
├── Employee_Show.java           # View/search/sort employee data
├── Employee_Remove.java         # Delete employee files
├── Employee_Update.java         # Update employee records
├── Employee_Payslip.java        # Payslip generation logic
├── Attendance.java              # Attendance marking/viewing
├── ExperienceCalculator.java    # Joining date and experience
├── MainMenu.java                # Menu design
├── Login.java                   # Admin login
├── CodeExit.java                # Exit message
├── backup/                      # Backup storage
├── attendance/                  # Attendance logs
├── file<ID>.txt                 # Employee records
```

---

### 🚀 How to Run

1. Clone the repository
2. Compile using any Java compiler:
   ```bash
   javac EMS.java
   ```
3. Run the program:
   ```bash
   java EMS
   ```
4. Use the default login credentials:
   - Username: `admin`
   - Password: `1234`
  
