import java.util.*;
import java.io.*;
import java.time.*;

class Employee_Payslip {
    public void generatePayslip(String id) {
        File file = new File("file" + id + ".txt");
        if (!file.exists()) {
            System.out.println("Employee does not exist.");
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            String name = "", position = "", salary = "";
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("Employee Name")) name = line.split(":")[1].trim();
                if (line.contains("Employee Position")) position = line.split(":")[1].trim();
                if (line.contains("Employee Salary")) salary = line.split(":")[1].trim();
            }

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

class Attendance {
    private final String attendanceFolder = "attendance";

    public Attendance() {
        File folder = new File(attendanceFolder);
        if (!folder.exists()) folder.mkdir();
    }

    public void markAttendance(String id) {
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
        try (Scanner sc = new Scanner(file)) {
            System.out.println("\nAttendance Record for Employee ID: " + id);
            while (sc.hasNextLine()) System.out.println(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Error reading attendance.");
        }
    }
}

class ExperienceCalculator {
    public void calculateExperience(String id) {
        File file = new File("file" + id + ".txt");
        if (!file.exists()) {
            System.out.println("Employee file not found.");
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            String joiningLine = "";
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

class Login {
    private final String USERNAME = "admin";
    private final String PASSWORD = "1234";

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t\t*** EMPLOYEE MANAGEMENT SYSTEM LOGIN ***");
        System.out.print("Enter Username: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
            System.out.println("\nLogin Successful!\n");
            return true;
        } else {
            System.out.println("\nInvalid Credentials. Access Denied.\n");
            return false;
        }
    }
}

class MainMenu {
    public void menu() {
        System.out.println("\n\nPress 1  : To Add an Employee Details");
        System.out.println("Press 2  : To See an Employee Details");
        System.out.println("Press 3  : To Remove an Employee");
        System.out.println("Press 4  : To Update Employee Details");
        System.out.println("Press 5  : To List All Employees");
        System.out.println("Press 6  : To Search Employee by Name");
        System.out.println("Press 7  : To Show Employee Count");
        System.out.println("Press 8  : To Show Employees by Minimum Salary");
        System.out.println("Press 9  : To Backup All Employee Files");
        System.out.println("Press 10 : To Sort Employees by Salary");
        System.out.println("Press 11 : To Show Highest Paid Employee");
        System.out.println("Press 12 : To Show Average Salary");
        System.out.println("Press 13 : To Update Progress Report");
        System.out.println("Press 14 : To Update Working Hours");
        System.out.println("Press 15 : To Generate Employee Payslip");
        System.out.println("Press 16 : To Mark Attendance");
        System.out.println("Press 17 : To View Attendance");
        System.out.println("Press 18 : To Calculate Experience");
        System.out.println("Press 19 : To Add Joining Date");
        System.out.println("Press 20 : To Exit the EMS Portal");
    }
}

class EmployDetail {
    String name;
    String father_name;
    String email;
    String position;
    String employ_id;
    String employ_salary;
    String employ_contact;
    String progress_report;
    String working_hours;

    public void getInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee's name --------: ");
        name = sc.nextLine();
        System.out.print("Enter Employee's Father name -: ");
        father_name = sc.nextLine();
        System.out.print("Enter Employee's ID ----------: ");
        employ_id = sc.nextLine();
        System.out.print("Enter Employee's Email ID ----: ");
        email = sc.nextLine();
        System.out.print("Enter Employee's Position ----: ");
        position = sc.nextLine();
        System.out.print("Enter Employee contact Info --: ");
        employ_contact = sc.nextLine();
        System.out.print("Enter Employee's Salary ------: ");
        employ_salary = sc.nextLine();
        System.out.print("Enter Progress Report --------: ");
        progress_report = sc.nextLine();
        System.out.print("Enter Working Hours ----------: ");
        working_hours = sc.nextLine();
    }
}

class Employee_Add {
    public void createFile() {
        Scanner sc = new Scanner(System.in);
        EmployDetail emp = new EmployDetail();
        emp.getInfo();
        try {
            File f1 = new File("file" + emp.employ_id + ".txt");
            if (f1.createNewFile()) {
                FileWriter myWriter = new FileWriter(f1);
                myWriter.write("Employee ID       :" + emp.employ_id + "\n" +
                        "Employee Name     :" + emp.name + "\n" +
                        "Father's Name     :" + emp.father_name + "\n" +
                        "Employee Contact  :" + emp.employ_contact + "\n" +
                        "Email Information :" + emp.email + "\n" +
                        "Employee Position :" + emp.position + "\n" +
                        "Employee Salary   :" + emp.employ_salary + "\n" +
                        "Progress Report   :" + emp.progress_report + "\n" +
                        "Working Hours     :" + emp.working_hours);
                myWriter.close();
                System.out.println("\nEmployee has been Added :)\n");
                System.out.print("\nPress Enter to Continue...");
                sc.nextLine();
            } else {
                System.out.println("\nEmployee already exists :(");
                System.out.print("\nPress Enter to Continue...");
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Employee_Show {
    public void viewFile(String s) throws Exception {
        File file = new File("file" + s + ".txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }

    public void listAllEmployees() {
        List<File> files = getEmployeeFiles();
        for (File file : files) displayFile(file);
    }

    public void searchByName(String name) {
        List<File> files = getEmployeeFiles();
        boolean found = false;
        for (File file : files) {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.toLowerCase().contains("employee name") && line.toLowerCase().contains(name.toLowerCase())) {
                        System.out.println("\nFile: " + file.getName());
                        viewFile(file.getName().replace("file", "").replace(".txt", ""));
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error searching file: " + file.getName());
            }
        }
        if (!found) System.out.println("No employee found with name: " + name);
    }

    public void employeeCount() {
        List<File> files = getEmployeeFiles();
        System.out.println("Total Employees: " + files.size());
    }

    public void showBySalary(int minSalary) {
        List<File> files = getEmployeeFiles();
        for (File file : files) {
            try (Scanner sc = new Scanner(file)) {
                boolean match = false;
                StringBuilder sb = new StringBuilder();
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    sb.append(line).append("\n");
                    if (line.contains("Employee Salary")) {
                        String[] parts = line.split(":");
                        if (parts.length > 1) {
                            int salary = Integer.parseInt(parts[1].trim());
                            if (salary >= minSalary) match = true;
                        }
                    }
                }
                if (match) {
                    System.out.println("\n------------------------\n" + sb);
                }
            } catch (Exception e) {
                System.out.println("Error reading: " + file.getName());
            }
        }
    }

    public void backupAll() {
        List<File> files = getEmployeeFiles();
        File backupDir = new File("backup");
        backupDir.mkdir();
        for (File file : files) {
            try {
                File dest = new File(backupDir, file.getName());
                try (FileInputStream fis = new FileInputStream(file);
                     FileOutputStream fos = new FileOutputStream(dest)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) fos.write(buffer, 0, length);
                }
            } catch (Exception e) {
                System.out.println("Error backing up: " + file.getName());
            }
        }
        System.out.println("Backup completed.");
    }

    public void sortBySalary() {
        List<File> files = getEmployeeFiles();
        files.sort(Comparator.comparingInt(file -> {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.contains("Employee Salary")) return Integer.parseInt(line.split(":")[1].trim());
                }
            } catch (Exception ignored) {}
            return 0;
        }));
        files.forEach(this::displayFile);
    }

    public void highestPaidEmployee() {
        List<File> files = getEmployeeFiles();
        File maxFile = null;
        int maxSalary = -1;
        for (File file : files) {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.contains("Employee Salary")) {
                        int sal = Integer.parseInt(line.split(":")[1].trim());
                        if (sal > maxSalary) {
                            maxSalary = sal;
                            maxFile = file;
                        }
                    }
                }
            } catch (Exception ignored) {}
        }
        if (maxFile != null) displayFile(maxFile);
        else System.out.println("No employees found.");
    }

    public void averageSalary() {
        List<File> files = getEmployeeFiles();
        int total = 0, count = 0;
        for (File file : files) {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.contains("Employee Salary")) {
                        total += Integer.parseInt(line.split(":")[1].trim());
                        count++;
                    }
                }
            } catch (Exception ignored) {}
        }
        System.out.println("Average Salary: " + (count > 0 ? total / count : 0));
    }

    private List<File> getEmployeeFiles() {
        File[] files = new File(".").listFiles((d, name) -> name.startsWith("file") && name.endsWith(".txt"));
        return files != null ? Arrays.asList(files) : new ArrayList<>();
    }

    private void displayFile(File file) {
        try (Scanner sc = new Scanner(file)) {
            System.out.println("\n------------------------");
            while (sc.hasNextLine()) System.out.println(sc.nextLine());
        } catch (Exception ignored) {}
    }
}

class Employee_Remove {
    public void removeFile(String ID) {
        File file = new File("file" + ID + ".txt");
        if (file.exists() && file.delete()) System.out.println("\nEmployee has been removed Successfully");
        else System.out.println("\nEmployee does not exist :( ");
    }
}

class Employee_Update {
    public void updateFile(String s, String o, String n) throws IOException {
        File file = new File("file" + s + ".txt");
        Scanner sc = new Scanner(file);
        String fileContext = "";
        while (sc.hasNextLine()) fileContext += "\n" + sc.nextLine();
        sc.close();
        fileContext = fileContext.replaceAll(o, n);
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(fileContext.trim());
        myWriter.close();
    }
}

class CodeExit {
    public void out() {
        System.out.println("\n*****************************************");
        System.out.println("$ cat Thank You For Using my Software :) ");
        System.out.println("*****************************************");
        System.out.println("\t\t/~ <0d3d by Abhinav Dubey\n");
        System.exit(0);
    }
}

public class EMS{
    public static void main(String arv[]) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();
        boolean isLoggedIn = false;

        for (int attempt = 0; attempt < 3; attempt++) {
            if (login.login()) {
                isLoggedIn = true;
                break;
            } else {
                System.out.println("Attempts left: " + (2 - attempt));
            }
        }

        if (!isLoggedIn) {
            System.out.println("Too many failed attempts. Exiting...");
            System.exit(0);
        }

        System.out.print("\033[H\033[2J");
        Employee_Show epv = new Employee_Show();
        MainMenu obj1 = new MainMenu();
        obj1.menu();

        while (true) {
            System.out.print("\nPlease Enter choice : ");
            int i = Integer.parseInt(sc.nextLine());
            switch (i) {
                case 1 -> new Employee_Add().createFile();
                case 2 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String s = sc.nextLine();
                    try {
                        epv.viewFile(s);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                case 3 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String s = sc.nextLine();
                    new Employee_Remove().removeFile(s);
                }
                case 4 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String I = sc.nextLine();
                    try {
                        epv.viewFile(I);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    Employee_Update epu = new Employee_Update();
                    System.out.println("Enter detail to update (e.g., name, email, etc.):");
                    String s = sc.nextLine();
                    System.out.println("Enter new value:");
                    String n = sc.nextLine();
                    try {
                        epu.updateFile(I, s, n);
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
                case 5 -> epv.listAllEmployees();
                case 6 -> {
                    System.out.print("Enter name to search: ");
                    String name = sc.nextLine();
                    epv.searchByName(name);
                }
                case 7 -> epv.employeeCount();
                case 8 -> {
                    System.out.print("Enter minimum salary: ");
                    int min = Integer.parseInt(sc.nextLine());
                    epv.showBySalary(min);
                }
                case 9 -> epv.backupAll();
                case 10 -> epv.sortBySalary();
                case 11 -> epv.highestPaidEmployee();
                case 12 -> epv.averageSalary();
                case 13 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String id = sc.nextLine();
                    Employee_Update updater = new Employee_Update();
                    try {
                        epv.viewFile(id);
                        System.out.print("Enter New Progress Report: ");
                        String report = sc.nextLine();
                        updater.updateFile(id, "Progress Report   :.*", "Progress Report   :" + report);
                        System.out.println("Progress updated!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 14 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String id = sc.nextLine();
                    Employee_Update updater = new Employee_Update();
                    try {
                        epv.viewFile(id);
                        System.out.print("Enter New Working Hours: ");
                        String hours = sc.nextLine();
                        updater.updateFile(id, "Working Hours     :.*", "Working Hours     :" + hours);
                        System.out.println("Working hours updated!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 15 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String id = sc.nextLine();
                    new Employee_Payslip().generatePayslip(id);
                }
                case 16 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String id = sc.nextLine();
                    new Attendance().markAttendance(id);
                }
                case 17 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String id = sc.nextLine();
                    new Attendance().viewAttendance(id);
                }
                case 18 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String id = sc.nextLine();
                    new ExperienceCalculator().calculateExperience(id);
                }
                case 19 -> {
                    System.out.print("\nEnter Employee ID: ");
                    String id = sc.nextLine();
                    new ExperienceCalculator().addJoiningDate(id);
                }
                case 20 -> new CodeExit().out();
                default -> System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
}