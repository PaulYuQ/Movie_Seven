package service;

import pojo.Admin;

import java.util.List;

/**
 * @author 宋敏超
 * @version 1.0
 * @date 2020/9/15 9:53
 */
public interface AdminService {
    /**
     * 查找所有管理员
     * @return
     */
    int findAllCount();

    /**
     * 查找所有管理员
     * @return
     */
    List<Admin> findPageAdmin(int page,int pageAmount);

    /**
     * 根据id查询到管理员
     * @param id
     * @return
     */
    Admin findAdminById(int id);

    /**
     * 根据用户名密码查询用户，登录用
     * @param name
     * @param password
     * @return
     */
    Admin findAdminByNamePassword(String name,String password);

    /**
     * 更新管理员信息
     * @param admin
     * @return
     */
    int updateAdmin(Admin admin);

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    int addAdmin(Admin admin);

    /**
     * 删除管理员
     * @param id
     * @return
     */
    int deleteAdmin(int id);



}
