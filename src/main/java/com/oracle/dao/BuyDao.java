package com.oracle.dao;

import java.util.Map;

public interface BuyDao {

    //查询账号和密码
    public Map<String,Object> queryUserByUsernameAndPassword(Map<String,Object> map);

    //添加用户

    public int addUser(Map<String,Object> map);
    //找回密码

    public int updateMima(Map<String,Object> map);
    //删除用户
    public int deleteUser(int hu_user_id);
}
