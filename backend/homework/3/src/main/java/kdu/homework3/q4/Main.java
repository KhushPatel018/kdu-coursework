package kdu.homework3.q4;

import kdu.homework3.logging.LoggingSystem;
import kdu.homework3.hospital.User;
import kdu.homework3.insurance.BlueCrossBlueShield;
import kdu.homework3.insurance.HealthInsurancePlan;
import kdu.homework3.insurance.InsuranceBrand;
import kdu.homework3.insurance.PlatinumPlan;

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
