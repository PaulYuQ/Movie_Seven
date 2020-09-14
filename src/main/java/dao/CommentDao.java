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
     *
     * @param movie_id 传入当前页面的movie_id
     * @return 返回类型为Comment的List集合
     */
    List<Comment> findCommentsByMovieId(Integer movie_id);
    List<Comment> getComments(Integer movie_id);
}
