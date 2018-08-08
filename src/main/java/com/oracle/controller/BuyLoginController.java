package com.oracle.controller;

import com.alibaba.fastjson.JSONObject;
import com.oracle.service.BuyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/buyLoginController")
public class BuyLoginController{
    @Autowired
    private BuyService buyService;
    @RequestMapping(value = "/login",produces = "text/html;charset=utf-8")

    public @ResponseBody String login(@RequestParam(value = "hu_user_name",required = false) String hu_user_name,
                                      @RequestParam(value = "hu_password",required = false) String hu_password,
                                      HttpSession session){
        Map<String, Object> login = buyService.login(hu_user_name, hu_password);
        JSONObject jobj=new JSONObject();
        if(login==null){
            jobj.put("isOk",false);
        }else{
            session.setAttribute("login",login);
            jobj.put("isOk",true);
            jobj.put("info","登录成功");
        }
        return jobj.toJSONString();
    }
    @RequestMapping("/tomain")
    public String toMain(){

        return "redirect:/goodsController/testModelAndView";

    }

    @RequestMapping("/logout")
    public String logout(){

        return "redirect:/jsp/login.jsp";
    }

    @RequestMapping("/zhuce")
    public String zhuce(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "sex") String sex,
                        @RequestParam(value = "birthday") String birthday,
                        @RequestParam(value = "identity") String identity,
                        @RequestParam(value = "email") String email,
                        @RequestParam(value = "mobile") String mobile,
                        @RequestParam(value = "address") String address,
                        HttpSession session){

            Map<String,Object> map=new HashMap<String,Object>();
            map.put("hu_user_name",username);
            map.put("hu_password",password);
            map.put("hu_sex",sex);
            map.put("hu_birthday",birthday);
            map.put("hu_email",email);
            map.put("hu_mobile",mobile);
            map.put("hu_address",address);
            map.put("hu_status",1);
        int zhuce = buyService.zhuce(map);
        JSONObject jsonObject=new JSONObject();
        if(zhuce==0){
            jsonObject.put("isOk",false);
            jsonObject.put("info","&#x6ce8;&#x518c;&#x5931;&#x8d25;");
            return "redirect:/jsp/register.jsp";
        }else{
            jsonObject.put("isOk",true);
            jsonObject.put("info","注册成功");
            session.setAttribute("map",map);
            return "redirect:/jsp/reg-result.jsp";

        }

    }
    @RequestMapping("/password")
    @ResponseBody
    public String password(@RequestParam(value = "hu_user_name",required = false) String hu_user_name,
                           @RequestParam(value = "hu_email",required = false) String hu_email,
                           @RequestParam(value = "hu_password",required = false) String hu_password,
                           HttpSession session){
          Map<String,Object> map=new HashMap<String,Object>();
          map.put("hu_user_name",hu_user_name);
          map.put("hu_email",hu_email);
          map.put("hu_password",hu_password);
        int mima = buyService.mima(map);
        if(mima==0){
            return "redirect:/jsp/retrieve_password.jsp";
        }else{
            return "redirect:/jsp/back.jsp";
        }


    }

}
