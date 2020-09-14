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
     * @return 返回类型为Comment的List集合
     */
    List<Comment> findCommentsByMovieId(Integer movie_id);

    /**
     *根据movie_id的查询并处理好的commits评论
     * @param movie_id 传入当前页面的movie_id
     * @return 返回类型为Comment的List集合
     */
    List<Comment> getComments(Integer movie_id);

    /**
     * 返回指定的movie_id的评论数量
     * @param movie_id
     * @return
     */
    Integer getCount(Integer movie_id);

}
