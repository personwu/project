package com.oracle.service;

import java.util.Map;

public interface BuyService {

      //登录

    public Map<String,Object> login(String hu_user_name,String hu_password);

    //注册

    public int zhuce(Map<String,Object> map);

    //修改密码
    public int mima(Map<String,Object> map);
}
