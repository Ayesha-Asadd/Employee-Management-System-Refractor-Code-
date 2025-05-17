package repository;

import domain.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findById(String id);
    List<Employee> findAll();
    void save(Employee e);
    void delete(String id);
}
