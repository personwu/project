package com.oracle.dao;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    public Map<String,Object> findAll();

    public int addStudent(Map<String,Object> map);

    public Map<String,Object> findStudentByid(int sid);
}
