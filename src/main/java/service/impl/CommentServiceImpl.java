package service.impl;

import dao.CommentDao;
import dao.UserDao;
import dao.impl.CommentDaoImpl;
import dao.impl.UserDaoImpl;
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
    public List<Comment> getComments(Integer movie_id) {
        return commentDao.getComments(movie_id);
    }
}
