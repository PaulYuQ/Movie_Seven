package dao.impl;

import dao.CommentDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Comment;
import util.DBUtil;

import java.sql.SQLException;
import java.util.*;

/**
 * @Author Miss kun
 * @Date 2020/9/13 18:54
 * @Version 1.0
 * CommentDao层的实现类
 */
public class CommentDaoImpl implements CommentDao {

    /**
     * 创建QueryRunner 对象
     */
    private QueryRunner qr;

    public CommentDaoImpl() {
        qr = new QueryRunner(DBUtil.getDataSource());
    }

    /**
     *返回没有处理的 comments 集合，flag：0，没有进行排序 ；flag = 1 ，按照时间排序
     * @param movie_id 传入当前页面的movie_id
     * @return
     */
    @Override
    public List<Comment> findCommentsByMovieId(Integer movie_id,Integer flag) {
        String sql = "";
        if (flag == 0){
             sql = "SELECT a.comment_id,a.movie_id,a.parent_id,a.user_id,b.name,a.content,a.date " +
                    " FROM comments AS a,`users` AS b WHERE \n" +
                    "a.user_id=b.user_id AND a.movie_id=?";
        }else {
            sql = "SELECT a.comment_id,a.movie_id,a.parent_id,a.user_id,b.name,a.content,a.date  FROM comments AS a,`users` AS b WHERE  \n" +
                    " a.user_id=b.user_id AND a.movie_id=? ORDER BY a.`date` DESC";
        }
        //执行sect语句
        List<Comment> commentList = null;
        try {
            commentList = qr.query(sql, new BeanListHandler<Comment>(Comment.class), new Object[]{movie_id});
            return commentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *对原始的comments集合进行处理
     * @param movie_id 传入当前电影id，获取所有评论。
     * @param flag 用于时间排序
     * @return 返回评论List集合(二级评论的格式)
     */
    @Override
    public List<Comment> getComments(Integer movie_id,Integer flag){
        //获取电影id为：movie_id 的所有评论。
        List<Comment> all = findCommentsByMovieId(movie_id,flag);
        if (all == null){
            System.out.println("该电影还没有人评论！！");
           return  null;
        }else{
            Map<Integer,Comment> map = new HashMap(100,0.75f);
            //用于存放最终的comment集合
            List<Comment> result = new ArrayList<>();
            for (Comment comment : all ){
                //判断父节点是否为空
                if (comment.getParent_id() == null){
                    result.add(comment);
                }else{
                    //存放父节点不为空,comment, key : comment_id ,value:comment
                    map.put(comment.getComment_id(),comment);
                }
            }
            //遍历那些父节点为空的 comment
            for (Comment comm : result){
                comm.setChild(new ArrayList<Comment>());
                Set<Integer> keys = map.keySet();
                Iterator<Integer> it = keys.iterator();
                //遍历map集合，查找parent_id 和 comment_id 相同的
                while (it.hasNext()){
                    Integer i = it.next();
                    Comment comment = map.get(i);
                    if (comm.getComment_id().equals(comment.getParent_id())){
                        comm.getChild().add(comment);
                        it.remove();
                    }else { }
                }
            }
            return  result;
        }
    }

    /**
     * 返回评论的总数
     * @param movie_id 查看movie_id的评论
     * @return 返回所有的评论
     */
    @Override
    public Integer getCount(Integer movie_id) {
        String sql = "select COUNT(*) from comments AS a ,users b where a.user_id=b.user_id AND a.movie_id=?";
        Integer count = 0;
        try {
            Long c = (Long)qr.query(sql, new ScalarHandler<>(), new Object[]{movie_id});
            count = c.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 返回处理好的comment评论数量的大小
     * @param movie_id 传入需要查询的movie_id的id
     * @param flag 用于时间排序
     * @return 返回一个Integer类型的数字
     */
    @Override
    public Integer getCountComments(Integer movie_id,Integer flag) {
        Integer comments = getCount(movie_id);
        if (comments == null){
            return 0;
        }else {
            return comments;
        }
    }

    /**
     * 添加评论
     * @param comments 传入一个comment评论
     * @return
     */
    @Override
    public Integer addComments(Comment comments) {
        String sql = "insert into comments(movie_id,parent_id,content,user_id)values(?,?,?,?)";
        int result = 0;
        try {
             result = qr.update(sql, new Object[]{
                    comments.getMovie_id(),
                    comments.getParent_id(),
                    comments.getContent(),
                    comments.getUser_id(),});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除评论
     * @param comment_id 根据comment_id 删除评论
     * @return
     */
    @Override
    public Integer deleteComments(Integer comment_id) {
        String sql = "delete from comments where comment_id=?";
        Integer result = 0;
        try {
            result = qr.update(sql,new Object[]{comment_id});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过parent_id获取子comment_id集合
     * @param parentId 父id
     * @return
     */
    @Override
    public List<Comment> getCommentIdByParentsId(Integer parentId) {
        String sql = "SELECT comment_id FROM comments WHERE parent_id = ?";
        try {
            List<Comment> query = qr.query(sql, new BeanListHandler<Comment>(Comment.class), new Object[]{parentId});
            return  query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


