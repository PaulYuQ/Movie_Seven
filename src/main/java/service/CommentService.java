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
     *返回处理好的comment集合
     * @param movie_id 传入当前页面的一个电影名的id
     * @param flag 用于时间排序
     * @return 返回comment的List集合
     */
    List<Comment> getComments(Integer movie_id,Integer flag);

    /**
     * 获取movie_id 的所有的评论
     * @param movie_id
     * @return
     */
    Integer getCount(Integer movie_id);


    /**
     * 返回处理好的评论数据数量
     * @param movie_id movie_id的id
     * @return 返回一个Integer类型的数据
     */
    Integer getCountComments(Integer movie_id);

    /**
     * 添加评论
     * @param comment 传入comment对象
     * @return 添加正确返回：1，错误返回：0
     */
     Integer addComment(Comment comment);

    /**
     * 删除评论
     * @param comment_id  传入评论id
     * @return 删除成功 ： 1 ，删除失败 ：0
     */
     Integer deleteComment(Integer comment_id);

    /**
     * 通过parent_id获取子comment_id集合
     * @param parentId 父id
     * @return 成功 ： list ，失败 ：null
     */
     List<Comment> getCommentsIdByParentId(Integer parentId);
}
