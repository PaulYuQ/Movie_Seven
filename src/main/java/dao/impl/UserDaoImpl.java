package dao.impl;

import dao.UserDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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
}
