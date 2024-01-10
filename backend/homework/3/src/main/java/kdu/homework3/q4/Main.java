package kdu.homework3.q4;

import kdu.homework3.Logging.LoggingSystem;
import kdu.homework3.HospitalMangement.User;
import kdu.homework3.InsuranceMangement.BlueCrossBlueShield;
import kdu.homework3.InsuranceMangement.HealthInsurancePlan;
import kdu.homework3.InsuranceMangement.InsuranceBrand;
import kdu.homework3.InsuranceMangement.PlatinumPlan;

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
