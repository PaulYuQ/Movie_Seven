package service;

import pojo.Comment;

import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/13 18:51
 * @Version 1.0
 * Comment(评论的业务层)
 */
public interface CommentService{
    /**
     *
     * @param movie_id 传入当前页面的一个电影名的id
     * @return
     */
    List<Comment> getComments(Integer movie_id);


}
