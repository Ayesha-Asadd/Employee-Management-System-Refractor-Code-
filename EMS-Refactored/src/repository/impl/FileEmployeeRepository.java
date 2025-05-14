package repository.impl;

import domain.Employee;
import repository.EmployeeRepository;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class FileEmployeeRepository implements EmployeeRepository {
    private final Path dir = Paths.get("employees");

    public FileEmployeeRepository() throws IOException {
        Files.createDirectories(dir);
    }

    @Override
    public Optional<Employee> findById(String id) {
        Path file = dir.resolve(id + ".txt");
        if (!Files.exists(file)) return Optional.empty();
        try {
            List<String> lines = Files.readAllLines(file);
            String name = lines.get(1).split(":")[1].trim();
            String position = lines.get(2).split(":")[1].trim();
            int salary = Integer.parseInt(lines.get(3).split(":")[1].trim());
            Employee e = new Employee(id, name, position, salary);
            if (lines.size() >= 5) e.setJoiningDate(LocalDate.parse(lines.get(4).split(":")[1].trim()));
            return Optional.of(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Employee> findAll() {
        try {
            List<Employee> list = new ArrayList<>();
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt");
            for (Path file : stream) {
                findById(file.getFileName().toString().replace(".txt", "")).ifPresent(list::add);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Employee e) {
        Path file = dir.resolve(e.getId() + ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write("ID:" + e.getId()); writer.newLine();
            writer.write("Name:" + e.getName()); writer.newLine();
            writer.write("Position:" + e.getPosition()); writer.newLine();
            writer.write("Salary:" + e.getSalary()); writer.newLine();
            if (e.getJoiningDate() != null) {
                writer.write("JoiningDate:" + e.getJoiningDate()); writer.newLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Files.deleteIfExists(dir.resolve(id + ".txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
