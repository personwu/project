package com.oracle.test;

import com.oracle.common.Md5AndSha;

import java.util.Arrays;

public class MessTest {

    public static void main(String args[])throws Exception{
        Md5AndSha md5AndSha=new Md5AndSha();
        byte [] bytes="测试".getBytes("utf-8");
        String str="dsafw4r42353";
        System.out.print(Arrays.toString(bytes)+"\n");
    }
}
