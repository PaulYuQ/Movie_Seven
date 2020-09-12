import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import org.junit.Test;
import service.impl.UserServiceImpl;
import util.DBUtil;

/**
 * @Author Miss kun
 * @Date 2020/9/12 15:02
 * @Version 1.0
 */
public class TestJunit{
    //测试工厂类
    @Test
    public void testFactory(){
        UserServiceImpl userService = BeanFactory.getInstance("UserService", UserServiceImpl.class);
        UserDaoImpl userDao = BeanFactory.getInstance("UserDao", UserDaoImpl.class);
        System.out.println(userService);
        System.out.println(userDao);
    }
    //测试连接池是否成功
    @Test
    public void testC3p0(){
        System.out.println(DBUtil.getConnection());
        System.out.println(DBUtil.getDataSource());
    }
}
