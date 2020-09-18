import dao.impl.CommentDaoImpl;
import factory.BeanFactory;
import org.junit.Test;
import pojo.Comment;
import pojo.User;
import service.CommentService;
import service.impl.CommentServiceImpl;
import service.impl.UserServiceImpl;

import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/12 15:02
 * @Version 1.0
 */
public class TestJunit{

    UserServiceImpl userService = BeanFactory.getInstance("UserLoginService", UserServiceImpl.class);

    @Test
    public void findAllUsers(){
        System.out.println(userService.findAllUsers());
    }

    @Test
    public void calCount(){
        System.out.println(userService.calCount());
    }
    @Test
    public void findPageUsers(){
        System.out.println(userService.findPageUsers(2, 2));
    };

    @Test
    public void findById(){
        System.out.println(userService.findById(2));
    }
    @Test
    public void addUsers(){
        for (int i = 0;i<12;i++){
            System.out.println(userService.addUser("刘逼熊", "123", "15800000000"));
        }
    }
    @Test
    public void deleteById(){
        System.out.println(userService.deleteById(15));
    }
    @Test
    public void updateUser(){
        System.out.println(userService.updateUser(new User("余强22","1233","12345678901")));
    }



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
