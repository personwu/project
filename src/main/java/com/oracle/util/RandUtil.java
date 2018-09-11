package com.oracle.util;

import java.util.Random;

public class RandUtil {

    public static String getRandNum(){
        String randNum = new Random().nextInt(10000000)+"";
        System.out.print("生成"+randNum);
        if(randNum.length()!=6){//如果生成的不是六位随机数则返回该方法继续生成

            return getRandNum();
        }
        return randNum;
    }
}
