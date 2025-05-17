package factory;

import domain.Payslip;

public class PayslipFactory {
    public static Payslip create(String id, String name, String position,
                                 int basic, double hra, double da, double gross) {
        return new Payslip(id, name, position, basic, hra, da, gross);
    }
}
