package service.impl;

import dao.CommentDao;
import dao.impl.CommentDaoImpl;
import factory.BeanFactory;
import pojo.Comment;
import service.CommentService;

import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/13 18:52
 * @Version 1.0
 * CommentService 的实现类
 */
public class CommentServiceImpl implements CommentService {


    /**
     * 创建CommentDao层对象
     */
    private CommentDao commentDao = null;

    /**
     * 初始化CommentDaoImpl对象
     */
    public CommentServiceImpl() {
        commentDao = BeanFactory.getInstance("CommentDao", CommentDaoImpl.class);
    }


    /**
     *
     * @param movie_id 传入当前页面的一个电影名的id
     * @return 返回一个参数类型为Comment类的List集合
     */
    @Override
    public List<Comment> getComments(Integer movie_id ,Integer flag) {
        return commentDao.getComments(movie_id,flag);
    }

    /**
     * 获取movie_id的所有的评论
     * @param movie_id
     * @return
     */
    @Override
    public Integer getCount(Integer movie_id) {
        return commentDao.getCount(movie_id);
    }

    /**
     *
     * @param movie_id movie_id的id
     * @return
     */
    @Override
    public Integer getCountComments(Integer movie_id) {
        return commentDao.getCountComments(movie_id,0);
    }

    /**
     *添加评论
     * @param comment 传入comment对象
     * @return
     */
    @Override
    public Integer addComment(Comment comment) {
        return commentDao.addComments(comment);
    }

    /**
     * 删除评论
     * @param comment_id  传入评论id
     * @return
     */
    @Override
    public Integer deleteComment(Integer comment_id) {
        return commentDao.deleteComments(comment_id);
    }

    /**
     * 通过parent_id获取子comment_id集合
     * @param parentId 父id
     * @return
     */
    @Override
    public List<Comment> getCommentsIdByParentId(Integer parentId) {
        return commentDao.getCommentIdByParentsId(parentId);
    }
}
