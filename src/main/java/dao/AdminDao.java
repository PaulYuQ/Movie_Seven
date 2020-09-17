package dao;

import pojo.Admin;

import java.util.List;

/**
 * @author 宋敏超
 * @version 1.0
 * @date 2020/9/15 8:36
 */
public interface AdminDao {
    /**
     * 返回总行数
     * @param
     * @return
     */
    int findAllCount();

    /**
     * 获取某页的管理员集合
     * @param page：第几页
     * @param pageAmount：一页放几个
     * @return
     */
    List<Admin> findPageAdmin(int page,int pageAmount);

    /**
     * 根据ID查找管理员
     * @param id 管理员ID
     * @return
     */
    Admin findAdminById(int id);

    /**
     * 根据ID删除管理员
     * @param id 管理员ID
     * @return 1为成功，-1为失败
     */
    int deleteAdminById(int id);

    /**
     * 根据管理员对象更新管理员信息
     * @param admin
     * @return
     */

    int updateAdmin(Admin admin);

    /**
     * 往数据库添加管理员信息、注册或根管理员操作
     * @param admin
     * @return
     */

    int addAdmin(Admin admin);

    /**
     * 根据用户名密码获取管理员，登录使用
     * @param name
     * @param password
     * @return
     */
    Admin findAdminByNamePassowrd(String name,String password);







}
