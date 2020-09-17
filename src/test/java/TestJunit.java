import dao.CollectionDao;
import dao.impl.CollectionDaoImpl;
import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import org.junit.Test;
import pojo.Collection;
import service.impl.UserServiceImpl;
import util.DBUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Miss kun
 * @Date 2020/9/12 15:02
 * @Version 1.0
 */
public class TestJunit{
    CollectionDao collectionDao = new CollectionDaoImpl();
    //测试工厂类
    @Test
    public void testFactory(){
        UserServiceImpl userService = BeanFactory.getInstance("UserService");
        UserDaoImpl userDao = BeanFactory.getInstance("UserDao");
        System.out.println(userService);
        System.out.println(userDao);
    }

    //测试连接池是否成功
    @Test
    public void testC3p0(){
        System.out.println(DBUtil.getConnection());
        System.out.println(DBUtil.getDataSource());
    }

    @Test
    public void testDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Test
    public void testInsertCollect() {
        Collection collection = new Collection();
        collection.setUser_id(5);
        collection.setMovie_id(6);
        System.out.println(collectionDao.insert(collection));
    }

    @Test
    public void testDeleteCollect() {
        System.out.println(collectionDao.delete(6));
    }

    @Test
    public void testUpdateCollect() {
        Collection collection = new Collection();
        collection.setCollection_id(4);
        collection.setUser_id(7);
        collection.setMovie_id(8);
        System.out.println(collectionDao.update(collection));
    }

    @Test
    public void testGetAll() {
        System.out.println(collectionDao.getAllCollect());
    }
    @Test
    public void testGetCollectById() {
        System.out.println(collectionDao.getCollectById(4));
    }

    /*@Test
    public void testGetMovieIdByKeyWord() {
        System.out.println(collectionDao.getMovieIdByKeyWord("本"));
    }*/

    @Test
    public void getMovieIdByKeyWordTest() {
        System.out.println(collectionDao.getMovieIdByKeyWord("人", 3 , 0, 6));
    }

    @Test
    public void getAllCollectionByKeyWordTest() {
        System.out.println(collectionDao.getAllCollectionByKeyWord("人", 3, 0, 6));
    }

    @Test
    public void testGetCollectionByPage() {
        System.out.println(collectionDao.getCollectionByPage(2));
    }

    @Test
    public void testCollectionVo() {
        System.out.println(collectionDao.getAllCollection(3,0,3));
    }
    @Test
    public void testGetAllCollectByUser() {
        System.out.println(collectionDao.getAllCollectByUser(3,0,3));
    }

    @Test
    public void testGetMovieByMovieId() {
        System.out.println(collectionDao.getMovieByMovieId(4));
    }
}
