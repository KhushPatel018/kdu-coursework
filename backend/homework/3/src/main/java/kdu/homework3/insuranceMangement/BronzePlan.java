package kdu.homework3.insuranceMangement;

public class BronzePlan extends HealthInsurancePlan {

    BronzePlan(){
        this.setCoverage(0.6);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return salary * 0.05;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.05 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}