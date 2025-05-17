package controller;

import domain.Employee;
import repository.EmployeeRepository;
import repository.AttendanceRepository;
import repository.impl.FileEmployeeRepository;
import repository.impl.FileAttendanceRepository;
import service.EmployeeService;
import service.AttendanceService;
import strategy.StandardSalaryCalculator;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import repository.ExperienceReportRepository;
import repository.RatingRepository;
import repository.impl.FileRatingRepository;
import repository.impl.InMemoryExperienceReportRepository;
import service.EmployeeFilterService;
import service.EmployeeRatingService;
import service.ExperienceReportService;

public class Main {
    public static void main(String[] args) throws IOException {
        EmployeeRepository employeeRepo = new FileEmployeeRepository();
        AttendanceRepository attendanceRepo = new FileAttendanceRepository();
        RatingRepository ratingRepo = new FileRatingRepository();
        ExperienceReportRepository expRepo = new InMemoryExperienceReportRepository();
        EmployeeRatingService ratingService = new EmployeeRatingService(ratingRepo);
        ExperienceReportService expService = new ExperienceReportService(expRepo);
        EmployeeFilterService filterService = new EmployeeFilterService(); // stateless, no repo

        EmployeeService employeeService = new EmployeeService(employeeRepo, new StandardSalaryCalculator());
        AttendanceService attendanceService = new AttendanceService(attendanceRepo);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to EMS!");
        while (true) {
            System.out.println("\n1. Add Employee\n2. List Employees\n3. Generate Payslip\n4. Mark Attendance\n5. View Attendance");
            System.out.println("6. Set Rating\n7. View Ratings\n8. Filter by Department\n9. Experience Report\n0. Exit");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("ID: "); String id = scanner.nextLine();
                    System.out.print("Name: "); String name = scanner.nextLine();
                    System.out.print("Position: "); String position = scanner.nextLine();
                    System.out.print("Salary: "); int salary = Integer.parseInt(scanner.nextLine());
                    System.out.print("Joining Date (YYYY-MM-DD): "); LocalDate date = LocalDate.parse(scanner.nextLine());
                    Employee e = new Employee(id, name, position, salary);
                    e.setJoiningDate(date);
                    employeeService.add(e);
                }
                case 2 -> employeeService.list().forEach(emp -> System.out.println(emp.getId() + ": " + emp.getName()));
                case 3 -> {
                    System.out.print("Employee ID: "); String id = scanner.nextLine();
                    var payslip = employeeService.generatePayslip(id);
                    System.out.println("Gross Salary: " + payslip.getGross());
                }
                case 4 -> {
                    System.out.print("Employee ID: "); String id = scanner.nextLine();
                    attendanceService.markToday(id);
                }
                case 5 -> {
                    System.out.print("Employee ID: "); String id = scanner.nextLine();
                    attendanceService.view(id).forEach(r -> System.out.println(r.getDate()));
                }
                case 6 -> {
                    System.out.print("Employee ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Rating (1-5): ");
                    int rating = Integer.parseInt(scanner.nextLine());
                    ratingService.setRating(id, rating);
                    System.out.println("Rating assigned.");
                }

                case 7 -> {
                    System.out.print("Employee ID: ");
                    String id = scanner.nextLine();
                    ratingService.getRating(id).ifPresentOrElse(
                        r -> System.out.println("Rating: " + r),
                        () -> System.out.println("No rating assigned.")
                    );
                }

                case 8 -> {
                    System.out.print("Enter Department Name: ");
                    String dept = scanner.nextLine();
                    var filtered = filterService.filterByDepartment(employeeService.list(), dept);
                    if (filtered.isEmpty())
                        System.out.println("No employees found in that department.");
                    else
                        filtered.forEach(e -> System.out.println(e.getId() + ": " + e.getName() + " - " + e.getPosition()));
                }

                case 9 -> {
                    var report = expService.generateReport(employeeService.list());
                    if (report.isEmpty()) {
                        System.out.println("No employees with joining dates found.");
                    } else {
                        for (var entry : report) {
                            String exp = expService.getFormattedExperience(entry.getKey().getJoiningDate());
                            System.out.println(entry.getKey().getId() + ": " + entry.getKey().getName() + " - " + exp);
                        }
                    }
                }
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
