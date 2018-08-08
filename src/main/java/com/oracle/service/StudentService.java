package com.oracle.service;

import java.util.Map;

public interface StudentService {

    public Map<String,Object> findAll();

    public int addStudent(Map<String,Object> map);

    public Map<String,Object> findStudentByid(int sid);
}
