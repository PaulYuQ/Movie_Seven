import dao.impl.HistoryDaoImpl;
import factory.BeanFactory;
import org.junit.Test;
import pojo.Histories;
import pojo.ShowHistory;
import service.HistoryService;
import service.impl.HistoryServiceImpl;
import util.DBUtil;

import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/12 15:02
 * @Version 1.0
 */
public class TestJunit{
    //测试工厂类
    @Test
    public void testFactory(){
        HistoryServiceImpl userService = BeanFactory.getInstance("HistoryService", HistoryServiceImpl.class);
        HistoryDaoImpl userDao = BeanFactory.getInstance("HistoryDao", HistoryDaoImpl.class);
        System.out.println(userService);
        System.out.println(userDao);
    }
    //测试连接池是否成功
    @Test
    public void testC3p0(){
        System.out.println(DBUtil.getConnection());
        System.out.println(DBUtil.getDataSource());
    }


    HistoryService movie=BeanFactory.getInstance("HistoryService", HistoryService.class);

    /**
     * create by: sky
     * create time: 8:58 2020/9/15
     * 增加测试
     * @Param:
     * @return void
     */
    @Test
    public void addTest(){
        int i=movie.movieAdd(new Histories(15,19,200));
        System.out.println(i);
    }


    /**
     * create by: sky
     * create time: 8:59 2020/9/15
     * 删除测试
     * @Param:
     * @return void
     */
    @Test
    public void deleteTest(){
        int i=movie.movieDelete(7);
        System.out.println(i);
    }


    /**
     * create by: sky
     * create time: 8:59 2020/9/15
     * 修改测试
     * @Param:
     * @return void
     */
    @Test
    public void updateTest(){
        Histories histories=new Histories(22,33,44);
        histories.setHistory_id(2);
        int i=movie.movieUpdate(histories);
        System.out.println(i);
    }

    /**
     * create by: sky
     * create time: 9:02 2020/9/15
     * 通过id进行查询测试
     * @Param:
     * @return void
     */
    @Test
    public void findTest(){
        Histories histories=movie.movieFindById(2);
        System.out.println(histories.toString());
    }


    /**
     * create by: sky
     * create time: 9:20 2020/9/15
     * 查询表格行数测试
     * @Param: 
     * @return void
     */
    @Test
    public void numberTest(){
        System.out.println(movie.movieNumber());
    }


    /**
     * create by: sky
     * create time: 9:25 2020/9/15
     * 分页查询测试
     * @Param:
     * @return void
     */
    @Test
    public void listTest(){
        List<Histories> histories=movie.movieList(1,3);
        for(Histories hs:histories){
            System.out.println(hs.toString());
        }
    }


    /**
     * create by: sky
     * create time: 9:33 2020/9/15
     * 用户浏览信息的行数查询测试
     * @Param:
     * @return void
     */
    @Test
    public void showNumberTest(){
        movie.historyNumber(15);
        System.out.println(movie.historyNumber(15));
    }

    /**
     * create by: sky
     * create time: 9:40 2020/9/15
     * 用户浏览信息测试
     * @Param:
     * @return void
     */
    @Test
    public void showTest(){
        List<ShowHistory> showHistories=movie.historyList(15,1,3);
        for(ShowHistory sh:showHistories){
            System.out.println(sh.toString());
        }
    }
}
