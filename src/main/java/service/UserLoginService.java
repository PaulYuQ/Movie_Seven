package service;

import pojo.User;

public interface UserLoginService {
    boolean login(String name,String pwd);
    boolean addUser(String name,String pwd,String phone);
    User findByName(String name);
}
