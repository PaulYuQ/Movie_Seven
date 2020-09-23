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
import java.util.Stack;

/**
 * @Author Miss kun
 * @Date 2020/9/12 15:02
 * @Version 1.0
 */
public class TestJunit {
    CollectionDao collectionDao = new CollectionDaoImpl();

    //测试工厂类
    @Test
    public void testFactory() {
        UserServiceImpl userService = BeanFactory.getInstance("UserService");
        UserDaoImpl userDao = BeanFactory.getInstance("UserDao");
        System.out.println(userService);
        System.out.println(userDao);
    }

    //测试连接池是否成功
    @Test
    public void testC3p0() {
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
        System.out.println(collectionDao.getMovieIdByKeyWord("人", 3, 0, 6));
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
        System.out.println(collectionDao.getAllCollection(3, 0, 3));
    }

    @Test
    public void testGetAllCollectByUser() {
        System.out.println(collectionDao.getAllCollectByUser(3, 0, 3));
    }

    @Test
    public void testGetMovieByMovieId() {
        System.out.println(collectionDao.getMovieByMovieId(4));
    }

    @Test
    public void calCollectionCountTest() {
        System.out.println(collectionDao.calCollectionCount());
    }

    @Test
    public void getUserCollectionByPageTest() {
        System.out.println(collectionDao.getUserCollectionByPage(1, 6));
    }

    @Test
    public void insertTest() {
        int temp = 44;
        for (int i = 0; i < 20; i++) {
            collectionDao.insert(new Collection(3, temp++, new Date()));
        }
    }

    /*@Test
    public void testBig() {
        String a1 = "1234567891";
        String a2 = "1234567891";
        StringBuffer result = new StringBuffer("");
        String[] split1 = a1.split("");
        String[] split2 = a2.split("");
        boolean flag = false;
        *//* result.append(Integer.parseInt(split1[0]) + Integer.parseInt(split2[0]));
         result.append(Integer.parseInt(split1[2]) + Integer.parseInt(split2[2]));*//*
        for (int i = split1.length - 1; i >= 0; i--) {
            int temp = (Integer.parseInt(split1[i]) + Integer.parseInt(split2[i]));
            if (temp > 9 && flag == false) {
                flag = true;
                temp %= 10;
                result.append(temp);
                continue;
            } else if (flag) {
                flag = false;
                temp += 1;
                if (temp > 9 && flag == false) {
                    flag = true;
                    temp %= 10;
                    result.append(temp);
                    continue;
                } else {
                    result.append(temp);
                    continue;
                }
            } else {
                result.append(temp);
                continue;
            }
        }
        result.reverse();
        System.out.println(result);
    }*/
}
