package kdu.homework3.insuranceMangement;

public interface InsuranceBrand {
    /**
     * calculate the monthly premium according to type of insurance plan
     * and other factors like age and smoking according to company which offers this
     * insurance in our case we have BlueCrossBlueShield
     * this method will be implemented by different companies
     *
     * @param insurancePlan insurance plan (platinum/gold/silver/bronze)
     * @param age age of benificary
     * @param smoking person smokes or not
     * @return Monthly Premium
     */
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking);

}