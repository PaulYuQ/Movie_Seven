package service.impl;

import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import service.UserLoginService;

public class LoginServiceImpl implements UserLoginService {
    private UserDaoImpl userDao;
    public LoginServiceImpl(){
        userDao= BeanFactory.getInstance("UserDao",UserDaoImpl.class);
    }
    @Override
    public boolean login(String name, String pwd) {
        return userDao.dologin(name,pwd);
    }

    @Override
    public boolean doRegister(String name, String pwd, String phone) {
        return userDao.doRegister(name,pwd,phone);
    }
}
