package com.oracle.common;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/6/1 0001.
 */
//MD5加密算法
public class Md5AndSha {


    /**
    * @param :要加密的字符串信息
    * @param digestType:加密类型，选择MD5或者SHA-1
     * @return 加密后的内容
    * */
    public String disgestString(String str,String digestType){
        MessageDigest md;
        String message="";
        try{
            md=MessageDigest.getInstance(digestType);
            md.update(str.getBytes());
            message=byteToHex1(md.digest());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return message;
    }


    /**
     * @param :将字节数组变成十六进制的字符串
     * @param bytes:字节数组
     * @return ：十六进制的字符串
    * */
     public String byteToHex1(byte[] bytes){

         StringBuffer sb=new StringBuffer(bytes.length*2);
         for(byte b:bytes){
             sb.append(String.format("%02x",new Integer(b & 0xff)));

         }
         return sb.toString();

     }

     /**
      * 将十六进制转换为字符串
     * */
     public byte[] HexToByte(String str){
         if (str==null||str.trim().equals("")){
             return new byte[0];
         }
         byte [] bytes=new byte[str.length()/2];
         for(int i=0;i<str.length()/2;i++){
             String subStr=str.substring(i*1,i*2+2);
             bytes[i]=(byte)Integer.parseInt(subStr,16);
         }
         return bytes;

     }
}
