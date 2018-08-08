package com.oracle.po;

public class UserInfo {


    public String username;
    public String password;
    public String age;
    public String name;
    public int id;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo(String username, String password, String age, String name, int id) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.name = name;
        this.id = id;
    }
}
