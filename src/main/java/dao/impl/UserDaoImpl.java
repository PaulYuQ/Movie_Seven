package dao.impl;

import dao.UserDao;
import org.apache.commons.dbutils.QueryRunner;
import util.DBUtil;

/**
 * @Author Miss kun
 * @Date 2020/9/10 11:04
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {

    //创建QueryRunner 对象
    private QueryRunner qr;

    public UserDaoImpl() {
        qr = new QueryRunner(DBUtil.getDataSource());
    }

}
