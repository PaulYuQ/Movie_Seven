package dao.impl;

import dao.HistoryDao;
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
 * @version: 1.0
 */
public class HistoryDaoImpl implements HistoryDao {

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
     * create time: 8:45 2020/9/15
     * 单个用户的浏览历史记录行数
     * @Param: id
     * @return long
     */
    @Override
    public long historyNumber(int id){
        String mysqlNumber="SELECT count(*) FROM histories,movies WHERE " +
                "histories.movie_id=movies.movie_id AND histories.user_id=?";
        try {
            return (long)queryRunner.query(mysqlNumber,new ScalarHandler<>(),id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }


    /**
     * create by: sky
     * create time: 8:48 2020/9/15
     *
     * @Param: id
     * @Param: page :当前页数
     * @Param: row :一页展示的行数
     * @return java.util.List<java.lang.Object>
     */
    @Override
    public List<ShowHistory> historyList(int id,int page,int row) {
        String mysqlFind = "SELECT movies.type,movies.name,movies.image_url,movies.actor,histories.progress,histories.history_id " +
                "FROM histories,movies WHERE  histories.movie_id=movies.movie_id AND histories.user_id=? LIMIT ?,?";
        int number=(page-1)*row;
        try {
            return queryRunner.query(mysqlFind, new BeanListHandler<ShowHistory>(ShowHistory.class), id,number,row);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     *表的增删改查
     */


    /**
     * create by: sky
     * create time: 16:26 2020/9/14
     * 对Histories表进行增加操作
     * 返回值大于0证明增加成功
     * @return int
     * @Param: histories
     */
    @Override
    public int movieAdd(Histories histories) {
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
    public int movieUpdate(Histories histories){
        String mysqlUpdate="UPDATE histories SET user_id=?,movie_id=?,progress=? WHERE history_id=?";
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
    public Histories movieFindById(int id){
        String mysqlFind="SELECT * FROM histories WHERE history_id=?";
        try {
            return queryRunner.query(mysqlFind,new BeanHandler<Histories>(Histories.class),id);
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
    public List<Histories> movieList(int page,int row){
        String mysqlList="SELECT * FROM histories LIMIT ?,?";
        int number=(page-1)*row;
        try {
            return queryRunner.query(mysqlList,new BeanListHandler<Histories>(Histories.class), number,row);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
