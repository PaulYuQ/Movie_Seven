package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import service.UserService;


/**
 * @Author Miss kun
 * @Date 2020/9/10 11:00
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {
    //创建dao层对象
    private UserDao userDao = null;

    public UserServiceImpl() {
        userDao = BeanFactory.getInstance("UserDao", UserDaoImpl.class);
    }


}
