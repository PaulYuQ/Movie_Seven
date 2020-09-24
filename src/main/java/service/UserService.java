package service;

import pojo.User;

import java.util.List;

public interface UserService {
    boolean login(String name,String pwd);
    boolean addUser(String name,String pwd,String phone);
    User findByName(String name);
    int updateUser(User user);
    User findById(int id);
    int deleteByName(String name);
    List<User> findAllUsers();
    long calCount();
    List<User> findPageUsers(Integer page,Integer pageAmount);
    int deleteById(Integer id);
}
