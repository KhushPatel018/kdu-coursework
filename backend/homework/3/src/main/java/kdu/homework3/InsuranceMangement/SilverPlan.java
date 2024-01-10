package kdu.homework3.InsuranceMangement;

public class SilverPlan extends HealthInsurancePlan {
    SilverPlan()
    {
        this.setCoverage(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return salary * 0.06;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.06 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}