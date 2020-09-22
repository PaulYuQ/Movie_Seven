package dao;

import pojo.Comment;

import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/13 18:53
 * @Version 1.0
 * Comment(评论)的dao 层
 */
public interface CommentDao{
    /**
     * 根据电影id的查询指定的comments
     * @param movie_id 传入当前页面的movie_id
     * @param  flag 用于时间排序
     * @return 返回类型为Comment的List集合
     */
    List<Comment> findCommentsByMovieId(Integer movie_id,Integer flag);

    /**
     *根据movie_id的查询并处理好的commits评论
     * @param movie_id 传入当前页面的movie_id
     * @param flag 用于时间排序
     * @return 返回类型为Comment的List集合
     */
    List<Comment> getComments(Integer movie_id,Integer flag);

    /**
     * 返回指定的movie_id的评论数量
     * @param movie_id
     * @return
     */
    Integer getCount(Integer movie_id);

    /**
     * 返回处理好的Comment的size大小
     * @param movie_id
     * @param flag
     * @return
     */
    Integer getCountComments(Integer movie_id,Integer flag);

    /**
     * 添加评论
     * @param comments 传入一个comment评论
     * @return 成功 返回1，否则返回0
     */
    Integer addComments(Comment comments);

    /**
     * 删除评论
     * @param comment_id
     * @return
     */
    Integer deleteComments(Integer comment_id);

    /**
     * 通过parent_id获取子comment_id集合
     * @param parentId 父id
     * @return 返回list集合
     */
    List<Comment> getCommentIdByParentsId(Integer parentId);


}
