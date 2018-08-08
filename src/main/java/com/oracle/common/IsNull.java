package com.oracle.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/6/2.
 */
public class IsNull {
    public static boolean paramsIsNull(Object params){
        if(params ==null || params.toString().trim().equals("") || params.toString().trim().equals("null") || params.toString().trim().equals("NULL")|| params.toString().trim().equals("[]")){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {
        Map<String,Object> m1=new HashMap<String,Object>();
        Map<String,Object> m2=new HashMap<String,Object>();
        Map<String,Object> m3=new HashMap<String,Object>();
        Map<String,Object> m4=new HashMap<String,Object>();
        Map<String,Object> m5=new HashMap<String,Object>();
        m1.put("commodity_sort",1);
        m2.put("commodity_sort",7);
        m3.put("commodity_sort",4);
        m4.put("commodity_sort",3);
        m5.put("commodity_sort",4);
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);
        System.out.println("------------------------------------------------------");
        System.out.println(list);
        System.out.println("------------------------------------------------------");
        YUtils.sortByCommodity(list,0,list.size()-1);
        System.out.println("------------------------------------------------------");
        System.out.println(list);
        System.out.println("------------------------------------------------------");
    }
}
