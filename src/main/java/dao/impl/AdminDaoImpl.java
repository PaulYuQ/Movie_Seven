package dao.impl;

import dao.AdminDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Admin;
import util.DBUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 宋敏超
 * @version 1.0
 * @date 2020/9/15 8:44
 */
public class AdminDaoImpl implements AdminDao {
    private QueryRunner qr;

    public AdminDaoImpl(){
        qr = new QueryRunner(DBUtil.getDataSource());
    }



    @Override
    public List<Admin> findAllAdmin() {
        String sql="select admin_id as id,name,password,phone,control from admins";
        List<Admin> adminList = null;
        try {
            adminList = qr.query(sql, new BeanListHandler<Admin>(Admin.class));
            return adminList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin findAdminById(int id) {

        String sql="select admin_id as id,name,password,phone,control from admins where admin_id = ?";
       Admin admin=null;
        try {
            admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),id);
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }

    @Override
    public int deleteAdminById(int id) {

        String sql="delete  from admins where admin_id =?";

        try {
            return qr.update(sql, id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int updateAdmin(Admin admin) {
        String sql="update admins set name = ?,password = ?,phone = ?,control = ? where admin_id = ?";

        try {
            return qr.update(sql,admin.getName(),admin.getPassword(),admin.getPhone(),admin.getControl(),admin.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int addAdmin(Admin admin) {
        String sql="insert into admins (name,password, phone,control) values (?,?,?,?);";

        try {
            return qr.update(sql,admin.getName(),admin.getPassword(),admin.getPhone(),admin.getControl());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Admin findAdminByNamePassowrd(String name, String password) {
        String sql="select admin_id as id,name,password,phone,control from admins where name = ? and password = ? ";
        Admin admin=null;
        try {
            admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),name,password);
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }
}
