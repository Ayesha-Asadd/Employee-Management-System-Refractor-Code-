package domain;

import java.time.LocalDate;

public class Employee {
    private final String id;
    private String name;
    private String position;
    private int salary;
    private LocalDate joiningDate;

    public Employee(String id, String name, String position, int salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }
}
