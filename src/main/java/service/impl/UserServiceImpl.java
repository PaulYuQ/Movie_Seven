package service.impl;

import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import pojo.User;
import service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao;
    public UserServiceImpl(){
        userDao= BeanFactory.getInstance("UserDao",UserDaoImpl.class);
    }
    @Override
    public boolean login(String name, String pwd) {
        return userDao.dologin(name,pwd);
    }

    @Override
    public boolean addUser(String name, String pwd, String phone) {
        return userDao.addUser(name,pwd,phone);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public int deleteByName(String name) {

        return userDao.deleteByName(name);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public long calCount() {
        return userDao.calCount();
    }

    @Override
    public List<User> findPageUsers(Integer page, Integer pageAmount) {
        return userDao.findPageUsers(page,pageAmount);
    }

    @Override
    public int deleteById(Integer id) {
        return userDao.deleteById(id);
    }
}
