package service.impl;

import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import pojo.User;
import service.UserLoginService;

public class UserLoginServiceImpl implements UserLoginService {
    private UserDaoImpl userDao;
    public UserLoginServiceImpl(){
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
}
