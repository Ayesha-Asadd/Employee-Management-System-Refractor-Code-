package domain;

import java.time.LocalDate;

public class AttendanceRecord {
    private final String employeeId;
    private final LocalDate date;

    public AttendanceRecord(String employeeId, LocalDate date) {
        this.employeeId = employeeId;
        this.date = date;
    }

    public String getEmployeeId() { return employeeId; }
    public LocalDate getDate() { return date; }
}
