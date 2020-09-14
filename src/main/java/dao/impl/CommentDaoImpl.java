package dao.impl;

import dao.CommentDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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

    //创建QueryRunner 对象
    private QueryRunner qr;

    public CommentDaoImpl() {
        qr = new QueryRunner(DBUtil.getDataSource());
    }

    /**
     *
     * @param movie_id 传入当前页面的movie_id
     * @return
     */
    @Override
    public List<Comment> findCommentsByMovieId(Integer movie_id) {
        String sql = "SELECT a.comment_id,a.movie_id,a.parent_id,a.user_id,b.name,a.content,a.date " +
                " FROM comments AS a,`users` AS b WHERE \n" +
                "a.user_id=b.user_id AND a.movie_id=?";
        //执行sect语句
        List<Comment> commentList = null;
        try {
            commentList = qr.query(sql, new BeanListHandler<Comment>(Comment.class), movie_id);
            return commentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param movie_id 传入当前电影id，获取所有评论。
     * @return 返回评论List集合
     */
    @Override
    public List<Comment> getComments(Integer movie_id){
        //获取电影id为：movie_id 的所有评论。
        List<Comment> all = findCommentsByMovieId(movie_id);
        System.out.println(all);
        if (all == null){
            System.out.println("该电影还没有人评论！！");
           return  null;
        }else{
            Map<Integer,Comment> map = new HashMap<>();
            List<Comment> result = new ArrayList<>();//用于存放最终的comment集合
            for (Comment comment : all ){
                if (comment.getParent_id() == null){//判断父节点是否为空
                    result.add(comment);
                }else{
                    //存放父节点不为空,comment
                    map.put(comment.getComment_id(),comment);//key : comment_id ,value:comment
                }
            }
            //遍历那些父节点为空的 comment
            for (Comment comm : result){
                //遍历map集合，查找parent_id 和 comment_id 相同的
                comm.setChild(new ArrayList<Comment>());
                Set<Integer> keys = map.keySet();
                Iterator<Integer> it = keys.iterator();
                while (it.hasNext()){
                    Integer i = it.next();
                    Comment comment = map.get(i);
                    if (comm.getComment_id() == comment.getParent_id()){
                        comm.getChild().add(comment);
                        it.remove();
                    }
                }
                /*for (Integer i : keys){
                    Comment comment = map.get(i);
                    if (comm.getComment_id() == comment.getParent_id()){
                        comm.getChild().add(comment);
                    }else {
                        System.out.println("没有改父节点！！");//有一个逻辑判断（要是没有这个父节点的评论）
                    }
                }*/
            }
            return  result;
        }
    }

/*    public List<Comment> getComments(Long newsId) {
        List<Comment> all = new ArrayList<>();
        Map<Long, Comment> map = new HashMap<>();
        List<Comment> result = new ArrayList<>();
        for (Comment c : all) {
            if (c.getParent_id() == null) {
                result.add(c);
            }
            //存放父节点不为空,comment
            map.put(c.commentId, c);
        }
        for (Comment c : all) {
            if (c.parentId != null) {
                //
                Comment parent = map.get(c.parentId);
                if (parent.child == null) {
                    parent.child = new ArrayList<>();
                }
                parent.child.add(c);
            }
        }
        return result;
    }*/

}


