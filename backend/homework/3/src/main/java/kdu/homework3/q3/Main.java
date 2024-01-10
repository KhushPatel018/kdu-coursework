package kdu.homework3.q3;


import kdu.homework3.logging.LoggingSystem;
import kdu.homework3.hospital.Doctor;
import kdu.homework3.insurance.HealthInsurancePlan;
import kdu.homework3.insurance.PlatinumPlan;

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
