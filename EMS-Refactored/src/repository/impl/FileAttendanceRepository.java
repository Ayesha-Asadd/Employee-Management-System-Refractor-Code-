package repository.impl;

import domain.AttendanceRecord;
import repository.AttendanceRepository;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FileAttendanceRepository implements AttendanceRepository {
    private final Path dir = Paths.get("attendance");

    public FileAttendanceRepository() throws IOException {
        Files.createDirectories(dir);
    }

    @Override
    public void mark(AttendanceRecord record) {
        Path file = dir.resolve("att_" + record.getEmployeeId() + ".txt");
        String line = record.getDate().toString();
        try {
            Files.write(file, (line + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AttendanceRecord> findByEmployeeId(String id) {
        Path file = dir.resolve("att_" + id + ".txt");
        if (!Files.exists(file)) return Collections.emptyList();
        try {
            return Files.readAllLines(file).stream()
                .map(LocalDate::parse)
                .map(d -> new AttendanceRecord(id, d))
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
