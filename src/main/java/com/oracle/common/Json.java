package com.oracle.common;




import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;


/*
* JSONObject方法是用详解
* JSONObject-lib包是一个beans,collections,maps,java arrays和xml和JSON互相转换的包。
 * */
public class Json {

    /*
    * 描述：json字符串转java代码
    * */
    public static void jsonTojava(){
        System.out.print("json字符串转java代码"+"/n");
        String jsonStr="{\"password\":\"123456\",\"username\":\"张三\"}";
        JSONObject jsonObject=JSONObject.fromObject(jsonStr);
        String username=jsonObject.getString("username");
        String password=jsonObject.getString("password");
        System.err.print("json--->java \n username="+username+"\t passwor="+password);
    }
    /*
    * java代码封装成json字符串
    * */
    public static void javaToJson(){
        System.out.print("java代码封装成json字符串");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("username","你好");
        jsonObject.put("age",24);
        jsonObject.put("sex","男");
        System.out.println("java--->json \n " + jsonObject.toString());

    }

    /*
    * json字符串转xml字符串
    *
    * */
    public static void jsonToxml(){

        System.out.print("json字符串转xml字符串");
        String jsonStr = "{\"username\":\"宋发元\",\"password\":\"123456\",\"age\":\"24\"}";
        JSONObject jsonObject=JSONObject.fromObject(jsonStr);
        XMLSerializer xmlSerializer=new XMLSerializer();
        xmlSerializer.setRootName("user_info");
        xmlSerializer.setTypeHintsEnabled(false);
        String xml=xmlSerializer.write(jsonObject);
        System.out.println("json--->xml \n" + xml);


    }
    /*
    * XML字符串转json字符串
    * */
    public static void xmlTojson(){

        System.out.print("xml字符串转json字符串");
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><user_info><password>123456</password><username>宋发元</username></user_info>";
        XMLSerializer xmlSerializer=new XMLSerializer();
        JSON json= xmlSerializer.read(xml);
        System.out.println("xml--->json \n" + json.toString());


    }
    /*
    * javaBean转json字符串
    * */
    /*public static void javaBeanTojson(){

        System.out.print("javaBean转json字符串");
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("你好");
        userInfo.setPassword("123456");
        JSONObject jsonObject=JSONObject.fromObject(userInfo);
        System.out.println("JavaBean-->json \n" + jsonObject.toString());
    }

    *//*
    * javaBean转xml字符串
    * *//*
    public static void javaBeanToxml(){
        System.out.print("javaBean转xml字符串");
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("songfayuan");
        userInfo.setPassword("66666");
        JSONObject jsonObject=JSONObject.fromObject(userInfo);
        XMLSerializer xmlSerializer=new XMLSerializer();
        String xml=xmlSerializer.write(jsonObject,"utf-8");
        System.out.println("javaBean--->xml \n" + xml);
    }*/

    public static void main(String args[]){
        jsonTojava();
        javaToJson();
        jsonToxml();
        xmlTojson();

    }
}
