package com.oracle.common;

public class GetBeforeMoney {

    public void test1(){

        double money = 19385;//税后工资
        double beforeTax = getPreTax(money);//扣税前
        double beforeSociety = getPreOther(beforeTax);//扣社保前
        double realMoney = beforeSociety;//实际到手工资

        System.out.print(
                String.format("税前:%.2f,社保:%.2f,扣税:%.2f,税前:%.2f",money,beforeSociety - beforeTax,
                        beforeTax - money,realMoney)
        );

    }

    /**
     * 使用标准的扣社保方式,没算上限，如 society = society < 23118? society : 23118;
     * if(money < 23118*(1-0.222)) {return money / (1-0.222);} else {return 23118*0.222 + money;}
     * 养老保险金：  800.00 (8%)   1900.00    (19%)
     * 医疗保险金：  200.00 (2%)   1000.00    (10%)
     * 失业保险金：  20.00  (0.2%) 80.00  (0.8%)
     * 基本住房公积金：    1200.00    (12%)  1200.00    (12%)
     * 补充住房公积金：    0.00   (0%)   0.00   (0%)
     * 工伤保险金：                40.00  (0.4%)
     * 生育保险金：                80.00  (0.8%)
     * @param money
     * @return
     */
    public double getPreOther(double money){

        return money / (1-0.222);
    }

    /**
     * 通过税后工资推算税前工资
     * @param money 税后工资
     * @return
     * */
    public double getPreTax(double money){

        double base = 3500;
        if(money < base + 1455){
            return (money - base * 0.03)/(1-0.03);
        }
        if(money < base + 4155){
            return (money - base * 0.1 - 105)/(1 - 0.1);
        }
        if(money < base + 7755){
            return (money - base * 0.2 - 555)/(1 - 0.2);
        }
        if(money < base + 27255){
            return (money - base * 0.25 - 1005)/(1 - 0.25);
        }
        if(money < base + 41255){
            return (money - base * 0.3 - 2755)/(1 - 0.3);
        }
        if(money < base + 57505){
            return (money - base * 0.35 - 5505)/(1 - 0.35);
        }
        return (money - base * 0.45 - 13505)/(1-0.45);
    }

}
