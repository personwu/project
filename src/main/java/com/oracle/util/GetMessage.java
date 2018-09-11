package com.oracle.util;


import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * 获取短信验证码类
 * */
public class GetMessage {

    /**
     * 用户Id
     * */
    public static final String ACCOUNT_SID = "7e04461fad07********7e478e3eec663";//这里填写在平台里边获取到的


    /**
    *密钥
    * */
    public static final String AUTH_TOKEN = "10aa253a6ece4********ae987032b66";

    /**
     * 请求地址前半部分
     * */

    public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";//这里是固定的不用改

    public static String randNum = RandUtil.getRandNum();

    public static String smsContent = "[*****]您的验证码为"+randNum+",请于"+2+"分钟内正确输入，如非本人操作，请忽略此短信";

    /**
     *(获取短信验证码)
     * */
    public static String getResult(String to) {
        randNum = RandUtil.getRandNum();
        String smsContent = "[*****]您的验证码为"+randNum+",请于"+2+"分钟内正确输入，如非本人操作，请忽略此短信";
        String args = QueryUtil.queryArguments(ACCOUNT_SID,AUTH_TOKEN,smsContent,to);
        OutputStreamWriter out = null;
        InputStream in = null;
        BufferedReader bf = null;
        StringBuffer sb = new StringBuffer();
        try{
            URL url = new URL(BASE_URL);
            URLConnection connection = url.openConnection();//打开链接
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);//设置链接超时
            connection.setReadTimeout(10000);//设置读取超时

            //提交数据
            out = new OutputStreamWriter(connection.getOutputStream(),"utf-8");
            out.write(args);
            out.flush();
            //读取返回的数据
            bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = bf.readLine()) != null){
                sb.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if( bf!=null){
                    bf.close();
                }
                if(out!=null){
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = JSONObject.fromObject(sb.toString());
        System.out.print(jsonObject);
        Object object = jsonObject.get("respCode");
        System.out.print("状态码:"+object+"验证码:"+randNum);
        System.out.print(!object.equals("00000"));
        if(!object.equals("00000")){
            return object.toString();
        }else{
            return randNum;
        }
    }



}
