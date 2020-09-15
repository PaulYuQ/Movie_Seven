package dao.impl;

import dao.MovieDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Histories;
import pojo.ShowHistory;
import util.DBUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/9/14 14:28
 * 对Histories表进行操作
 * @version: 1.0
 */
public class HistoryDaoImpl implements MovieDao {

    QueryRunner queryRunner = null;

    /**
     * create by: sky
     * create time: 15:32 2020/9/14
     * 普通的构造函数
     * @return
     * @Param:
     */
    public HistoryDaoImpl() {
        queryRunner = new QueryRunner(DBUtil.getDataSource());
    }

    /**
     * create by: sky
     * create time: 15:49 2020/9/14
     * 返回查询出来的用户的浏览历史
     * 用户的历史记录
     * @return java.util.List<pojo.ShowHistory>
     * @Param: id
     */
    public List<ShowHistory> findHistoryById(String id) {
        String mysqlFind = "SELECT movies.name,movies.image_url,movies.actor,histories.progress,histories.history_id " +
                "FROM histories,movies WHERE  histories.movie_id=movies.movie_id";
        try {
            return queryRunner.query(mysqlFind, new BeanListHandler<ShowHistory>(ShowHistory.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * create by: sky
     * create time: 16:26 2020/9/14
     * 对Histories表进行增加操作
     * 返回值大于0证明增加成功
     * @return int
     * @Param: histories
     */
    @Override
    public int movieAdd(Object o) {
        Histories histories=(Histories)o;
        String mysqlAdd = "INSERT INTO histories(user_id,movie_id,progress) VALUES (?,?,?)";
        try {
            return queryRunner.update(mysqlAdd, histories.getUser_id(), histories.getMovie_id(), histories.getProgress());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * create by: sky
     * create time: 17:11 2020/9/14
     * 对Histories表进行删除操作
     * 返回值大于0证明删除成功
     * @Param: id
     * @return int
     */
    @Override
    public int movieDelete(int id){
        String mysqlDelete="DELETE FROM histories WHERE history_id=?";
        try {
            return queryRunner.update(mysqlDelete,new Object[]{id});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * create by: sky
     * create time: 19:05 2020/9/14
     * 对Histories表进行修改操作
     * 返回值大于0证明修改成功
     * @Param: histories
     * @return int
     */
    @Override
    public int movieUpdate(Object o){
        String mysqlUpdate="UPDATE histories SET user_id=?,movie_id=?,progress=? WHERE history_id=?";
        Histories histories=(Histories)o;
        try {
            return queryRunner.update(mysqlUpdate, histories.getUser_id(),histories.getMovie_id()
                    ,histories.getProgress(),histories.getHistory_id());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * create by: sky
     * create time: 19:11 2020/9/14
     * 对Histories表进行单个查询操作
     * 通过history_id进行查询
     * @Param: id
     * @return pojo.Histories
     */
    @Override
    public Object movieFindById(int id){
        String mysqlFind="SELECT * FROM histories WHERE history_id=?";
        try {
            return queryRunner.query(mysqlFind,new BeanHandler<Object>(Histories.class),id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * create by: sky
     * create time: 19:29 2020/9/14
     *对histories进行行数查询
     * 返回long类型的值
     * @Param:
     * @return long
     */
    @Override
    public long movieNumber(){
        String mysqlNumber="SELECT count(*) FROM histories";
        try {
            return (long)queryRunner.query(mysqlNumber,new ScalarHandler<>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    /**
     * create by: sky
     * create time: 19:48 2020/9/14
     * 分页查询列表
     * @Param: page:当前页数
     * @Param: row:一页展示的行数
     * @return java.util.List<pojo.Histories>
     */
    @Override
    public List<Object> movieList(int page,int row){
        String mysqlList="SELECT * FROM histories LIMIT ?,?";
        int number=(page-1)*row;
        try {
            return queryRunner.query(mysqlList,new BeanListHandler<Object>(Histories.class), number,row);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
