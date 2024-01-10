package kdu.homework3.insuranceMangement;

public class GoldPlan extends HealthInsurancePlan {

    GoldPlan(){
        this.setCoverage(0.8);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return salary * 0.07;
    }
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}