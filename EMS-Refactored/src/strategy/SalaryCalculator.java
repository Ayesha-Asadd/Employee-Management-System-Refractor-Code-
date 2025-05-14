package strategy;

import domain.Employee;
import domain.Payslip;

public interface SalaryCalculator {
    Payslip calculate(Employee e);
}
