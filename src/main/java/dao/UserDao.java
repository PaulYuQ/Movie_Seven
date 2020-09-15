package dao;

import pojo.User;

import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/10 11:03
 * @Version 1.0
 */
public interface UserDao {
    boolean dologin(String name,String pwd);
    boolean doRegister(String name,String pwd,String phone);
}

