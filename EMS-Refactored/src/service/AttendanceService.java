package service;

import domain.AttendanceRecord;
import repository.AttendanceRepository;

import java.time.LocalDate;
import java.util.List;

public class AttendanceService {
    private final AttendanceRepository repo;

    public AttendanceService(AttendanceRepository repo) {
        this.repo = repo;
    }

    public void markToday(String id) {
        repo.mark(new AttendanceRecord(id, LocalDate.now()));
    }

    public List<AttendanceRecord> view(String id) {
        return repo.findByEmployeeId(id);
    }
}
