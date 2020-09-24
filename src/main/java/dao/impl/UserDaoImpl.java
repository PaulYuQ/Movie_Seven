package dao.impl;

import dao.UserDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.User;
import util.DBUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/10 11:04
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {

    /**
     * 创建QueryRunner 对象
     */
    private QueryRunner qr;

    public UserDaoImpl() {
        qr = new QueryRunner(DBUtil.getDataSource());
    }

    @Override
    public boolean dologin(String name,String pwd) {
        User user =null;
        String sql = "select *from users where name=? and password=?";

        try {
            user =qr.query(sql,new BeanHandler<>(User.class),name,pwd);
            if (user!=null){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByName(String name){
        String sql = "select *from users where name =?";
        User user = null;
        try{
            user = qr.query(sql,new BeanHandler<>(User.class),name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int updateUser(User user) {
        String sql = "update users set password=?,phone=? where name =?";
        try {
            return qr.update(sql,new Object[]{user.getPassword(),user.getPhone(),user.getName()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User findById(int id) {
        String sql = "select *from users where user_id =?";
        User user = null;
        try{
            user = qr.query(sql,new BeanHandler<>(User.class),id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int deleteByName(String name) {
        String sql = "delete from users where name=?";
        try {
            return qr.update(sql,new Object[]{name});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from users where User_id=?";
        try {
            return qr.update(sql,new Object[]{id});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long calCount() {
        String sql ="select count(*) from users";
        long res =0;
        try {
            res = qr.query(sql,new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean addUser(String name, String pwd, String phone) {
        int res = 0;
        String sql = "insert into users(name,password,phone) values(?,?,?)";
        try {
            res = qr.update(sql,new Object[]{name,pwd,phone});
            if (res>0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "select *from users";
        List<User> users = null;
        try {
            users = qr.query(sql,new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> findPageUsers(Integer page,Integer pageAmount) {
        List<User> users =null;
        String sql ="select *from users limit ?,?";
        try {
            users = qr.query(sql,new BeanListHandler<>(User.class),new Object[]{(page-1)*pageAmount,pageAmount});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
