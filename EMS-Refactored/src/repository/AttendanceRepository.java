package repository;

import domain.AttendanceRecord;
import java.util.List;

public interface AttendanceRepository {
    void mark(AttendanceRecord record);
    List<AttendanceRecord> findByEmployeeId(String id);
}
