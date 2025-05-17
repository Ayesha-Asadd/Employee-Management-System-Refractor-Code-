package service;

import domain.Employee;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeFilterService {
    public List<Employee> filterByDepartment(List<Employee> employees, String deptName) {
        String keyword = deptName.toLowerCase();
        return employees.stream()
                .filter(e -> e.getPosition().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }
}
