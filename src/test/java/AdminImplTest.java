import dao.impl.AdminDaoImpl;
import org.junit.Test;
import pojo.Admin;

import java.util.List;

/**
 * @author 宋敏超
 * @version 1.0
 * @date 2020/9/15 8:51
 */
public class AdminImplTest {
    @Test
    public void testFindAll(){
        AdminDaoImpl adminDao=new AdminDaoImpl();
        int page = adminDao.findAllCount();
        System.out.println(page);
    }

    @Test
    public void testFindPage(){
        AdminDaoImpl adminDao=new AdminDaoImpl();
        List<Admin> allAdmin = adminDao.findPageAdmin(2,2);
        System.out.println(allAdmin);
    }

    @Test
    public void testFindAdmin(){
        AdminDaoImpl adminDao=new AdminDaoImpl();
        Admin admin = adminDao.findAdminById(16);
        System.out.println(admin);
    }
    @Test
    public void testLogin(){
        AdminDaoImpl adminDao=new AdminDaoImpl();
        Admin admin = adminDao.findAdminByNamePassowrd("俞鸿波","0");
        System.out.println(admin);
    }

    @Test
    public void testDeleteAdmin(){
        AdminDaoImpl adminDao=new AdminDaoImpl();
        System.out.println(adminDao.deleteAdminById(2));

    }

    @Test
    public void testInsertAdmin(){
        AdminDaoImpl adminDao=new AdminDaoImpl();
        for(int i=20;i>0;i--)
        {
            System.out.println(adminDao.addAdmin(new Admin("df"+i,"123456"+i,"16352147890")));

        }
        System.out.println(adminDao.addAdmin(new Admin("df","123456","16352147890")));
        System.out.println(adminDao.addAdmin(new Admin("lsx","123456")));

    }

    @Test
    public void testUpdateAdmin(){
        AdminDaoImpl adminDao=new AdminDaoImpl();
        System.out.println(adminDao.updateAdmin(new Admin(1,"俞鸿波","0","16352147890",0)));


    }
}
