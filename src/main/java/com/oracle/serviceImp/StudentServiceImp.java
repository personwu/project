package com.oracle.serviceImp;

import com.oracle.dao.StudentDao;
import com.oracle.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public Map<String, Object> findAll() {
        Map<String,Object> student=new HashMap<String,Object>();
        Map<String, Object> all = studentDao.findAll();
        return all;
    }

    @Override
    public int addStudent(Map<String, Object> map) {
        int i = studentDao.addStudent(map);
        return i;
    }

    @Override
    public Map<String, Object> findStudentByid(int sid) {
        Map<String,Object> stu=new HashMap<String,Object>();
        Map<String, Object> studentByid = studentDao.findStudentByid(sid);


        return studentByid;
    }
}
