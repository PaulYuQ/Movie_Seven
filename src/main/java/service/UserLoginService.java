package service;

public interface UserLoginService {
    boolean login(String name,String pwd);
    boolean doRegister(String name,String pwd,String phone);
}
