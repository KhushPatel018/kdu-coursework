package kdu.homework3.q2;

import kdu.homework3.hospital.Patient;
import kdu.homework3.insurance.Billing;
import kdu.homework3.insurance.HealthInsurancePlan;
import kdu.homework3.insurance.PlatinumPlan;
import kdu.homework3.logging.LoggingSystem;

public class Main {
    private static final LoggingSystem ls = new LoggingSystem();
    public static void main(String[] args) {
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        Patient patient = new Patient();
        patient.setInsurancePlan(insurancePlan);
        double[] payments = Billing.computePaymentAmount(patient, 1000.0);
        ls.logInfo("Amount for company  : " + payments[0] + " $ and Amount for patient : " + payments[1] + " $");
    }
}
