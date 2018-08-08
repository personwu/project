package com.oracle.serviceImp;

import com.oracle.dao.BuyDao;
import com.oracle.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class BuyServiceImp  implements BuyService {

    @Autowired
    private BuyDao buyDao;
    @Override
    public Map<String, Object> login(String hu_user_name, String hu_password) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("hu_user_name",hu_user_name);
        map.put("hu_password",hu_password);
        Map<String, Object> buy = buyDao.queryUserByUsernameAndPassword(map);
        return buy;
    }

    @Override
    public int zhuce(Map<String, Object> map) {
        map.put("hu_user_name",map.get("hu_user_name"));
        map.put("hu_password",map.get("hu_password"));
        map.put("hu_sex",map.get("hu_sex"));
        map.put("hu_birthday",map.get("hu_birthday"));
        map.put("hu_identity_code",map.get("hu_identity_code"));
        map.put("hu_email",map.get("hu_email"));
        map.put("hu_mobile",map.get("hu_mobile"));
        map.put("hu_address",map.get("hu_address"));
        map.put("hu_status",1);
        int i = buyDao.addUser(map);
        return i;
    }

    @Override
    public int mima(Map<String, Object> map) {
        Map<String,Object> mima=new HashMap<String, Object>();
        mima.put("hu_user_name",map.get("hu_user_name"));
        mima.put("hu_email",map.get("hu_email"));
        mima.put("hu_password",map.get("hu_password"));
        int i = buyDao.updateMima(mima);
        return i;
    }
}
