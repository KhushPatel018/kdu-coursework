package kdu.homework3.insurance;

public class PlatinumPlan extends HealthInsurancePlan {
    public PlatinumPlan()
    {
        this.setCoverage(0.9);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return salary * 0.08;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.08 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
