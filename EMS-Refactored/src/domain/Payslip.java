package domain;

public class Payslip {
    private final String id;
    private final String name;
    private final String position;
    private final int basic;
    private final double hra;
    private final double da;
    private final double gross;

    public Payslip(String id, String name, String position, int basic, double hra, double da, double gross) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.basic = basic;
        this.hra = hra;
        this.da = da;
        this.gross = gross;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getBasic() { return basic; }
    public double getHra() { return hra; }
    public double getDa() { return da; }
    public double getGross() { return gross; }
}
