package com.oracle.test;


import org.junit.Test;

public class GetMoney {



     @Test
     public void test() {
         double money = 30000;//税前工资
         double society = getOther(money,money);//应扣社保
         double tax = getTax(money - society);
         double realMoney = money - society - tax;//实际到手工资
         System.out.print(
                 String.format("税前:%.2f,社保:%.2f,扣税:%.2f,到手:%.2f",money,society,tax,realMoney)
         );


     }

        /**
         * 计算应扣除的社保缴费金额，没算上限，如society = society < 23118 society:23118
         * @param society 社保基数
         * @param house 公积金基数
         * @Return
         * */

    public double getOther(double society,double house) {

        //养老保险金：800.00(8%) 1900 (19%)
        //医疗保险金：200.00(2%) 1000.00(10%)
        //失业保险金：20.00(0.2%) 80.00(0.8%)
        //基本住房公积金：1200.00(12%) 1200.00 (12%)
        //补充住房公积金：0.00(0%) 0.00(0%)
        //工伤保险金 40.00(0.4)
        //生育保险金：80.00(0.8%)
        return society * 0.08 + society * 0.02 +society * 0.02 + house * 0.12;


    }

    /**
     * 计算应扣税额
     * @param total
     * @return
     * */
    public double getTax(double total){
        double base = 3500;
        double money = total - base;
        if(money <= 0){
            return 0;
        }
        if(money < 1500){
            return money * 0.03;
        }
        if(money < 4500){
            return money * 0.1 - 105;
        }
        if(money < 9000){
            return money * 0.2 - 555;
        }
        if(money < 35000){
            return money * 0.25 - 1005;
        }
        if(money < 55000){
            return money * 0.3 -2755;
        }
        if(money < 80000){
            return money * 0.35 - 5505;
        }
        return money * 0.45 - 13505;
    }

}

