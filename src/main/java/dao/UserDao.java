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
    boolean addUser(String name,String pwd,String phone);
    User findByName(String name);
    int updateUser(User user);
    User findById(int id);
    int deleteByName(String name);
}

