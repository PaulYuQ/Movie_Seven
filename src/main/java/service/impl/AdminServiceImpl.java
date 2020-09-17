package service.impl;

import dao.AdminDao;
import factory.BeanFactory;
import pojo.Admin;
import service.AdminService;

import java.util.List;

/**
 * @author 宋敏超
 * @version 1.0
 * @date 2020/9/15 10:38
 */
public class AdminServiceImpl implements AdminService {
    AdminDao adminDao=null;

    public AdminServiceImpl(){
        adminDao= BeanFactory.getInstance("AdminDao",AdminDao.class);
    }
    @Override
    public int findAllCount() {
        return adminDao.findAllCount();
    }

    @Override
    public List<Admin> findPageAdmin(int page, int pageAmount) {
        return adminDao.findPageAdmin(page,pageAmount);
    }

    @Override
    public Admin findAdminById(int id) {
        return adminDao.findAdminById(id);
    }

    @Override
    public Admin findAdminByNamePassword(String name, String password) {
        return adminDao.findAdminByNamePassowrd(name,password);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminDao.updateAdmin(admin);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminDao.addAdmin(admin);
    }

    @Override
    public int deleteAdmin(int id) {
        return adminDao.deleteAdminById(id);
    }
}
