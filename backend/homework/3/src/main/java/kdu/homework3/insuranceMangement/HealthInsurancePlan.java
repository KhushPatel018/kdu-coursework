package kdu.homework3.insuranceMangement;

public abstract class HealthInsurancePlan {
    double coverage;
    private InsuranceBrand offeredBy;

    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand brand) {
        this.offeredBy = brand;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }
    public double getCoverage() {
        return coverage;
    }
    /**
     * calculate the monthly premium according to type of insurance plan
     * and salary of the staff member
     * @param salary salary of the person or member of staff
     * @return monthly premium
     */
    public abstract double computeMonthlyPremium(double salary);

    /**
     * calculate the monthly premium according to salary
     * and other factors like age and smoking according to plans which offered like
     * (platinum/gold/silver/bronze)
     *
     * @param salary insurance plan (platinum/gold/silver/bronze)
     * @param age age of benificary
     * @param smoking person smokes or not
     * @return Monthly Premium
     */
    public abstract double computeMonthlyPremium(double salary, int age, boolean smoking);


}
