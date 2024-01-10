package kdu.homework3.insuranceMangement;

import kdu.homework3.Logging.LoggingSystem;
import kdu.homework3.hospitalMangement.Patient;


public class Billing {

    private Billing(){

    }
    private static final LoggingSystem ls = new LoggingSystem();

    /**
     * this function uses patient's insurance plan to give respective payments that
     * should be made by patient and insurance company respectively also accounts for discounts
     * @param patient patient details
     * @param amount bill amount
     * @return two values first cost to insurance company second cost to patient
     */
    public static double[] computePaymentAmount(Patient patient, double amount) {

        double[] payments = new double[2];

        try{
            HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();
            if(patientInsurancePlan instanceof PlatinumPlan){
                payments[0] = patientInsurancePlan.getCoverage() * amount;
                payments[1] = amount - payments[0] - 50;
            }else if(patientInsurancePlan instanceof GoldPlan){
                payments[0] = patientInsurancePlan.getCoverage() * amount;
                payments[1] = amount - payments[0] - 40;
            } else if (patientInsurancePlan instanceof SilverPlan) {
                payments[0] = patientInsurancePlan.getCoverage() * amount;
                payments[1] = amount - payments[0] - 30;
            } else if (patientInsurancePlan instanceof BronzePlan) {
                payments[0] = patientInsurancePlan.getCoverage() * amount;
                payments[1] = amount - payments[0] - 25;
            }else {
                payments[0] = 0;
                payments[1] = amount - 20;
                throw new NullPointerException("not taken any insurance plan");
            }
        }catch (NullPointerException e){
            ls.logWarn(e.getMessage());
        }

        return payments;
    }
}
