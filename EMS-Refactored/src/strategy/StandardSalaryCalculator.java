package strategy;

import domain.Employee;
import domain.Payslip;
import factory.PayslipFactory;

public class StandardSalaryCalculator implements SalaryCalculator {
    @Override
    public Payslip calculate(Employee e) {
        double hra = e.getSalary() * 0.10;
        double da  = e.getSalary() * 0.08;
        double gross = e.getSalary() + hra + da;
        return PayslipFactory.create(
            e.getId(), e.getName(), e.getPosition(),
            e.getSalary(), hra, da, gross
        );
    }
}
