package kdu.homework3.insurance;

import kdu.homework3.logging.LoggingSystem;


public class BlueCrossBlueShield implements InsuranceBrand {

    private static final LoggingSystem ls = new LoggingSystem();

    @Override
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        double premium = 0;

        if (insurancePlan instanceof PlatinumPlan) {
            premium += age > 55 ? 200 : 0;
            premium += smoking ? 100 : 0;
        } else if (insurancePlan instanceof GoldPlan) {
            premium += age > 55 ? 150 : 0;
            premium += smoking ? 90 : 0;
        } else if (insurancePlan instanceof SilverPlan) {
            premium += age > 55 ? 100 : 0;
            premium += smoking ? 80 : 0;
        } else if (insurancePlan instanceof BronzePlan) {
            premium += age > 55 ? 50 : 0;
            premium += smoking ? 70 : 0;
        } else {
            logInsurancePlanError();
        }

        return premium;
    }

    private void logInsurancePlanError() {
        ls.logWarn("Insurance plan not recognized or provided");
    }

}
