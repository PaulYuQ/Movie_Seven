package dao;

import pojo.User;

import java.util.List;

/**
 * @Author lao liu
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
    int deleteById(Integer id);
    long calCount();
    List<User> findAllUsers();
    List<User> findPageUsers(Integer page,Integer pageAmount);

}

