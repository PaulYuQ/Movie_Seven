package dao.impl;

import dao.MovieDao;
import dao.ShowHistoryDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.ShowHistory;
import util.DBUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/9/15 8:34
 * @version: 1.0
 */
public class ShowHistoryDaoImpl implements ShowHistoryDao {
    QueryRunner queryRunner = null;

    /**
     * create by: sky
     * create time: 15:32 2020/9/14
     * 普通的构造函数
     * @return
     * @Param:
     */
    public ShowHistoryDaoImpl() {
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
    public List<Object> historyList(int id,int page,int row) {
        String mysqlFind = "SELECT movies.type,movies.name,movies.image_url,movies.actor,histories.progress,histories.history_id " +
                "FROM histories,movies WHERE  histories.movie_id=movies.movie_id AND histories.user_id=? LIMIT ?,?";
        int number=(page-1)*row;
        try {
            return queryRunner.query(mysqlFind, new BeanListHandler<Object>(ShowHistory.class), id,number,row);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
