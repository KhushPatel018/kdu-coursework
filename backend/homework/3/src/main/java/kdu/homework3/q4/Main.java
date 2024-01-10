package kdu.homework3.q4;

import kdu.homework3.Logging.LoggingSystem;
import kdu.homework3.hospitalMangement.User;
import kdu.homework3.insuranceMangement.BlueCrossBlueShield;
import kdu.homework3.insuranceMangement.HealthInsurancePlan;
import kdu.homework3.insuranceMangement.InsuranceBrand;
import kdu.homework3.insuranceMangement.PlatinumPlan;

public class Main {
    private static final LoggingSystem ls = new LoggingSystem();
    public static void main(String[] args) {
        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();

        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        ls.logInfo("Monthly insurance premium : " +  insurancePlan.computeMonthlyPremium(5000, 56, true)+ " $");
    }
}
