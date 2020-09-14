package dao.impl;

import dao.UserDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.User;
import util.DBUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/10 11:04
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {

    //创建QueryRunner 对象
    private QueryRunner qr;

    public UserDaoImpl() {
        qr = new QueryRunner(DBUtil.getDataSource());
    }
    /**
     * 获取指定id的movie comment信息
     * @return
     */
    @Override
    public List getCommentInfo() {
        String sql ="SELECT a.commentId,a.newsId,a.parentId,a.newsId,b.nickname,b.avatar,a.content,a.date " +
                "FROM comments AS a," +
                "users AS b WHERE a.newsId=#{newsId} AND a.userId=b.userId";
        try {
            qr.query(sql,new BeanListHandler(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
