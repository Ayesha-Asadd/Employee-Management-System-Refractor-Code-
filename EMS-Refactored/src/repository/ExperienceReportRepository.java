package repository;

import domain.Employee;
import java.time.Period;
import java.util.List;
import java.util.Map;

public interface ExperienceReportRepository {
    List<Map.Entry<Employee, Period>> computeExperience(List<Employee> employees);
}
