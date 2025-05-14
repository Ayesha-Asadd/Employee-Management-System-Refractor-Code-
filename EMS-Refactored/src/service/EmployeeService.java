package service;

import domain.Employee;
import domain.Payslip;
import repository.EmployeeRepository;
import strategy.SalaryCalculator;

import java.util.List;
import java.util.NoSuchElementException;

public class EmployeeService {
    private final EmployeeRepository repo;
    private final SalaryCalculator calculator;

    public EmployeeService(EmployeeRepository repo, SalaryCalculator calculator) {
        this.repo = repo;
        this.calculator = calculator;
    }

    public void add(Employee e) { repo.save(e); }
    public void remove(String id) { repo.delete(id); }
    public List<Employee> list() { return repo.findAll(); }
    public Payslip generatePayslip(String id) {
        Employee e = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        return calculator.calculate(e);
    }
}
