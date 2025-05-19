
// Import required libraries for file handling, utilities, and date/time operations
import java.util.*;
import java.io.*;
import java.time.*;

// Class to generate an employee's payslip using details from the employee's file
class Employee_Payslip {
    public void generatePayslip(String id) {
        File file = new File("file" + id + ".txt");

        // Check if the employee file exists
        if (!file.exists()) {
            System.out.println("Employee does not exist.");
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            String name = "", position = "", salary = "";

            // Parse required fields from the employee file
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("Employee Name"))
                    name = line.split(":")[1].trim();
                if (line.contains("Employee Position"))
                    position = line.split(":")[1].trim();
                if (line.contains("Employee Salary"))
                    salary = line.split(":")[1].trim();
            }

            // Display payslip with basic salary and allowances
            System.out.println("\n----------- Employee Payslip -----------");
            System.out.println("Employee ID      : " + id);
            System.out.println("Employee Name    : " + name);
            System.out.println("Position         : " + position);
            System.out.println("Basic Salary     : " + salary);
            System.out.println("HRA (10%)        : " + (Integer.parseInt(salary) * 0.10));
            System.out.println("DA (8%)          : " + (Integer.parseInt(salary) * 0.08));
            System.out.println("Gross Salary     : " + (Integer.parseInt(salary) * 1.18));
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Class to mark and view attendance records for employees
class Attendance {
    private final String attendanceFolder = "attendance";

    public Attendance() {
        File folder = new File(attendanceFolder);
        if (!folder.exists())
            folder.mkdir(); // Ensure attendance folder exists
    }

    public void markAttendance(String id) {
        // Append today's date to the employee's attendance file
        try (FileWriter writer = new FileWriter(attendanceFolder + "/attendance_" + id + ".txt", true)) {
            writer.write("Present on: " + LocalDate.now() + "\n");
            System.out.println("Attendance marked for today.");
        } catch (IOException e) {
            System.out.println("Error marking attendance.");
        }
    }

    public void viewAttendance(String id) {
        File file = new File(attendanceFolder + "/attendance_" + id + ".txt");
        if (!file.exists()) {
            System.out.println("No attendance record found.");
            return;
        }
        // Display attendance record line by line
        try (Scanner sc = new Scanner(file)) {
            System.out.println("\nAttendance Record for Employee ID: " + id);
            while (sc.hasNextLine())
                System.out.println(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Error reading attendance.");
        }
    }
}

// Class to calculate and store the experience of employees
class ExperienceCalculator {
    public void calculateExperience(String id) {
        File file = new File("file" + id + ".txt");
        if (!file.exists()) {
            System.out.println("Employee file not found.");
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            String joiningLine = "";

            // Locate the joining date from file
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("Joining Date")) {
                    joiningLine = line.split(":")[1].trim();
                    break;
                }
            }

            if (joiningLine.isEmpty()) {
                System.out.println("Joining Date not found. Please add it first.");
                return;
            }

            LocalDate joiningDate = LocalDate.parse(joiningLine);
            Period exp = Period.between(joiningDate, LocalDate.now());
            System.out.printf("Experience of Employee %s: %d years, %d months, %d days\n",
                    id, exp.getYears(), exp.getMonths(), exp.getDays());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addJoiningDate(String id) {
        Scanner sc = new Scanner(System.in);
        Employee_Update updater = new Employee_Update();
        try {
            File file = new File("file" + id + ".txt");
            if (!file.exists()) {
                System.out.println("Employee not found.");
                return;
            }

            System.out.print("Enter Joining Date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            FileWriter fw = new FileWriter(file, true);
            fw.write("\nJoining Date       :" + date);
            fw.close();
            System.out.println("Joining Date added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}