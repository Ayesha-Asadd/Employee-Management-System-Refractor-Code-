package service;

import domain.Employee;
import repository.ExperienceReportRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class ExperienceReportService {
    private final ExperienceReportRepository repo;

    public ExperienceReportService(ExperienceReportRepository repo) {
        this.repo = repo;
    }

    public List<Map.Entry<Employee, Period>> generateReport(List<Employee> employees) {
        return repo.computeExperience(employees);
    }

    public String getFormattedExperience(LocalDate joiningDate) {
        Period p = Period.between(joiningDate, LocalDate.now());
        return p.getYears() + " years, " + p.getMonths() + " months, " + p.getDays() + " days";
    }
}
