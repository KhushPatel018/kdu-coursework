package kdu.homework3.InsuranceMangement;

import kdu.homework3.Logging.LoggingSystem;


public class BlueCrossBlueShield implements InsuranceBrand {

    private static final LoggingSystem ls = new LoggingSystem();

    @Override
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
            double premium = 0;
        try{

            if(insurancePlan instanceof PlatinumPlan){
                if(age > 55) premium+= 200;
                if(smoking) premium+=100;
            }else if(insurancePlan instanceof GoldPlan){
                if(age > 55) premium+= 150;
                if(smoking) premium+=90;
            } else if (insurancePlan instanceof SilverPlan) {
                if(age > 55) premium+= 100;
                if(smoking) premium+=80;
            } else if (insurancePlan instanceof BronzePlan) {
                if(age > 55) premium+= 50;
                if(smoking) premium+=70;
            }else {
                throw new NullPointerException("not taken any insurance plan");
            }
        }catch (NullPointerException e){
            ls.logWarn(e.getMessage());
        }
        return premium;
    }


}
