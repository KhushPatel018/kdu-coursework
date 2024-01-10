package kdu.homework3.insurance;

import kdu.homework3.logging.LoggingSystem;


public class BlueCrossBlueShield implements InsuranceBrand {

    private static final LoggingSystem ls = new LoggingSystem();

    @Override
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
            double premium = 0;
        try{

            if(insurancePlan instanceof PlatinumPlan){
                premium+= age > 55 ? 200 : 0;
                premium+= smoking ? 100 : 0;
            }else if(insurancePlan instanceof GoldPlan){
                premium+= age > 55 ? 150 : 0;
                premium+= smoking ? 90 : 0;
            } else if (insurancePlan instanceof SilverPlan) {
                premium+= age > 55 ? 100 : 0;
                premium+= smoking ? 80 : 0;
            } else if (insurancePlan instanceof BronzePlan) {
                premium+= age > 55 ? 50 : 0;
                premium+= smoking ? 70 : 0;
            }else {
                throw new NullPointerException("not taken any insurance plan");
            }
        }catch (NullPointerException e){
            ls.logWarn(e.getMessage());
        }
        return premium;
    }


}
