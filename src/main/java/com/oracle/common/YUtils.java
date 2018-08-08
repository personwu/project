package com.oracle.common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YUtils {

    /**
    * <code>formatMap</code>方法可以将多个map类型的数据合并到一个吗map中
     * 建议放入Map<String,Object>类型的数据
     * @param maps 任意个map类型的数据
     * @return 合并之后的map
    *
    * */

    public static Map<String,Object> formatMap(Map<String,Object>...maps){

        Map<String,Object> pd=new HashMap<String,Object>();
        for(int i=0;i<maps.length;i++){
            if(maps[i]!=null){
                Iterator<Map.Entry<String, Object>> iterator = maps[i].entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, Object> next = iterator.next();
                    pd.put(next.getKey(),next.getValue());
                }
            }
        }
        return pd;
    }

    /**
     *<code>formatMap</code>方法可以将多个map类型的数据合并到一个map中，
     *建议放入Map<String,Object>类型的数据
     * @param maps 任意个map类型数据
     * @return 合并之后的map
     */

    public static LinkedHashMap<String,Object> formatLinkedMap(Map<String,Object>... maps){

        LinkedHashMap<String,Object> pd=new LinkedHashMap<String,Object>();
        for(int i=0;i<maps.length;i++){
            if(maps[i]!=null){
                Iterator<Map.Entry<String, Object>> iterator = maps[i].entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, Object> next = iterator.next();
                    pd.put(next.getKey(),next.getValue());
                }
            }
        }
        return pd;
    }


    /**
     * <code>mosaic</code>方法可以将多个字符串以ascii码顺序排列组合在一起，分隔符为‘，’
     *      * @param strings 任意数量的字符串
     * @return 拼接之后的字符串
     * */

    public static String mosaic(String... strings){
        Arrays.sort(strings);
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<strings.length;i++){
            if(i==strings.length-1){
                stringBuffer.append(strings[i]);
                break;
            }
              stringBuffer.append(strings[i]+",");
        }
        return stringBuffer.toString();
    }


    /**
     * 分页方法
     * 注意使用此方法需要提前查询总条数
     *
     * @param request 传进来一个request对象，一般就将controller层里的request传入即可
     * @param pagesize 每页的条数
     * @param allsize 总共的条数
     * @param CLASS 传入一个class对象，基本为service对象，或者是包含查询方法的对象
     * @param object 传入一个CLASS参数的实例化对象
     * @param MethodName 传入一个查询方法名称
     * @param parameterTypes 传入该查询方法的参数类型的class对象
     * @param URL 传入一个controller的URL地址
     * @return
     *
     * */

    public HttpServletRequest dopage(HttpServletRequest request,
                                     int pagesize,
                                     int allsize,
                                     Class CLASS,
                                     Object object,
                                     String MethodName,
                                     Class<Map> parameterTypes,
                                     String URL){


        PageUtil pageUtil = new PageUtil(pagesize);
        pageUtil.setAllsize(allsize);
        if(!(IsNull.paramsIsNull("currentPage"))) {
            pageUtil.setCurrentpage(Integer.parseInt(request.getParameter("currentPage")));
        }else{
            pageUtil.setCurrentpage(1);
        }
        HashMap<String,Object> page=new HashMap<String,Object>();
        page.put("first",pageUtil.getFirst()-1);
        page.put("last",pageUtil.getPagesize());

        HashMap<String, Object> key = new HashMap<String,Object>();
        if(request.getParameter("keyWord")!=null&&request.getParameter("keyWord")!=""){
            String keyWord=YUtils.StringtoJson(request.getParameter("keyWord"));
            YUtils.StringToMap(keyWord);
            request.setAttribute("keyword",String.valueOf(request.getParameter("keyWord")));
        }
        Map<String,Object> flag=YUtils.formatMap(page,key);
        Method queryBook1=null;
        Object invoke=null;

            try {
                queryBook1=CLASS.getMethod(MethodName,parameterTypes);
                invoke=queryBook1.invoke(CLASS.cast(object),flag);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }catch (InvocationTargetException e){
                e.printStackTrace();
            }
            Map<String,Object> map=new HashMap<String,Object>();
            request.setAttribute("page",pageUtil);
            request.setAttribute("requestURL",URL);
            try{
                request.setAttribute("list",(List<Map<String,Object>>) invoke);
            }catch (ClassCastException e){
                System.out.print("转换list失败，现尝试转换成MAP");
                request.setAttribute("list",(Map<String,Object>) invoke);
            }

        return request;

    }




    /**
     *
     * @param string ['a':'1']格式的json串
     * @return    {'a':'1'}格式的json串
     */
    public static String StringtoJson(String string){
        return string.replace("[", "{").replace("]", "}").replace(";","'");
    }
    public static String JsontoString(String string){
        return string.replace("{", "[").replace("}", "]").replace("\"",";");
    }


    /**
     * 此方法将json字符串转为Map类型
     * @param s
     * @return  返回Map类型数据
     */
    public static Map<String,Object> StringToMap(String s){
        GsonBuilder gb=new GsonBuilder();
        Gson g=gb.create();
        Map<String,Object> o = g.fromJson(s, new TypeToken<Map<String, Object>>() {
        }.getType());
        return o;
    }

    public static Map<String,String> StringToMap2(String s){
        GsonBuilder gb=new GsonBuilder();
        Gson g=gb.create();
        Map<String,String> o = g.fromJson(s, new TypeToken<Map<String, String>>() {
        }.getType());
        return o;
    }

    /**
     * 此接口返回一个六位随机数的字符串
     * @return
     *  */
    public static String randomCode(){
        long l=(Math.abs(new Random().nextLong())+new Date().getTime())%999999;
        if(l<100000L){
            l+=100000L;
        }
        return l+"";
    }

    /**
     * 此接口返回一个八位随机数的字符串
     * @return
     * */

    public static String randomCode8(){
         long l=(Math.abs(new Random().nextLong())+new Date().getTime())%999999;
         if(l<100000L){
             l+=100000L;
         }
         return l+"";
    }

    /**
     * 此接口返回一个订单号，算法是根据当前时间与用户的手机号以特定的方式进行组合，确保不会出现重复
     * @param userphone 用户的手机号
     * @return          唯一的订单号
     * */

    public static String getOrderNum(String userphone){
        if(IsNull.paramsIsNull(userphone)){
            Random random=new Random();
            long l=Math.abs(random.nextLong())%100000000000L;
            if(l<10000000000L)
                l+=10000000000L;
                userphone=l+"";
            }
            String a=new Date().getTime()+"";
            char[] date=a.toCharArray();
            char[] phone=userphone.toCharArray();
            String end="";
            for(int i=0;i<date.length;i++){
                if(i<phone.length){
                    end+=(date[i]+""+phone[i]);
                }else{
                    end +=date[i];
                }
            }
            return end;
    }

    public static void sortByCommodity(List<Map<String,Object>> list,int low,int hign){
        int i,j;
        Map<String,Object> x;
        if(low<hign){
            i=low;
            j=hign;
            x=list.get(i);

            while (i<j){
                while(i<j&&Integer.valueOf(String.valueOf(list.get(j).get("commodity_sort")))<Integer.valueOf(String.valueOf(x.get("commodity_sort"))))
                   j--;
                if(i<j){
                    list.set(i,list.get(j));
                    i++;
                }
                while(i<j&&Integer.valueOf(String.valueOf(list.get(i).get("commodity_sort")))>Integer.valueOf(String.valueOf(x.get("commodity_sort"))))
                    i++;
                if(i<j){
                    list.set(j,list.get(i));
                    j--;
                }
            }
            list.set(i,x);
            sortByCommodity(list,low,i-1);
            sortByCommodity(list,i+1,hign);

        }
    }

    public static Map<String,Object> getMap(String... flags){

        HashMap<String, Object> map = new HashMap<>();
        for(String flag:flags){
            String [] split=flag.split(",");
            map.put(split[0],split[1]);
        }
        return map;

    }

    public static Map<String,String> getStringMap(String... flags){

        HashMap<String, String> map = new HashMap<String,String>();
        for(String flag:flags){
            String[] split=flag.split(",");
            map.put(split[0],split[1]);
        }
        return map;

    }

    public static boolean matches(String regex,String str){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        return matcher.matches();
    }

}
