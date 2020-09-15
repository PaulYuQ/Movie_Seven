import dao.impl.CommentDaoImpl;
import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import org.junit.Test;
import pojo.Comment;
import service.CommentService;
import service.impl.CommentServiceImpl;
import util.DBUtil;

import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/12 15:02
 * @Version 1.0
 */
public class TestJunit{
    //测试工厂类
    @Test
    public void testFactory(){
    }
    //测试连接池是否成功
    @Test
    public void testC3p0(){
    }

    //测试输出Comments(评论集合)
    @Test
    public void testComments(){
        CommentService commentService = BeanFactory.getInstance("CommentService", CommentServiceImpl.class);
        CommentDaoImpl commentDao = BeanFactory.getInstance("CommentDao",CommentDaoImpl.class);
        List<Comment> comments = commentService.getComments(1);

        //List<Comment> comments = commentDao.findCommentsByMovieId(1);
        System.out.println("打印信息！！");
        for (Comment comment : comments){
            System.out.println(comment+"\n");

        }
    }
}
