package service.impl;

import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import service.LoginService;

public class LoginServiceImpl implements LoginService {
    private UserDaoImpl userDao;
    public LoginServiceImpl(){
        userDao= BeanFactory.getInstance("UserDao",UserDaoImpl.class);
    }
    @Override
    public boolean login(String name, String pwd) {
        return userDao.dologin(name,pwd);
    }
}
