package kdu.homework3.q3;


import kdu.homework3.Logging.LoggingSystem;
import kdu.homework3.hospitalMangement.Doctor;
import kdu.homework3.insuranceMangement.HealthInsurancePlan;
import kdu.homework3.insuranceMangement.PlatinumPlan;

public class Main {
    private static final LoggingSystem ls = new LoggingSystem();
    public static void main(String[] args)
    {
        Doctor doctor = new Doctor();
        doctor.setSalary(100000);
        HealthInsurancePlan plan = new PlatinumPlan();
        ls.logInfo(" Monthly insurance premium : " + plan.computeMonthlyPremium(doctor.getSalary()) + " $");
    }
}
