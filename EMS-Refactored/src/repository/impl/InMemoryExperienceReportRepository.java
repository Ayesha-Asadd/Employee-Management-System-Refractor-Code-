package repository.impl;

import domain.Employee;
import repository.ExperienceReportRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryExperienceReportRepository implements ExperienceReportRepository {

    @Override
    public List<Map.Entry<Employee, Period>> computeExperience(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.getJoiningDate() != null)
                .map(e -> Map.entry(e, Period.between(e.getJoiningDate(), LocalDate.now())))
                .sorted((a, b) -> {
                    Period pa = a.getValue(), pb = b.getValue();
                    return Long.compare(totalDays(pb), totalDays(pa)); // Descending
                })
                .collect(Collectors.toList());
    }

    private long totalDays(Period p) {
        return p.getYears() * 365L + p.getMonths() * 30 + p.getDays();
    }
}
