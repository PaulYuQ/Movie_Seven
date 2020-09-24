import com.alibaba.fastjson.JSON;
import dao.impl.CommentDaoImpl;
import dao.impl.UserDaoImpl;
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
    public CommentService commentService = null;
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
        List<Comment> comments = commentService.getComments(1,0);

        //List<Comment> comments = commentDao.findCommentsByMovieId(1);
        System.out.println("打印信息！！");
        for (Comment comment : comments){
            System.out.println(comment+"\n");
        }
        String string = JSON.toJSONString("\n\n\n"+comments);
        System.out.println(string);
    }

    /**
     * 测试获取处理好的comment数量
     */
    @Test
    public void testCountComments(){
        commentService = BeanFactory.getInstance("CommentService", CommentServiceImpl.class);
        Integer count = commentService.getCountComments(1);
        System.out.println(commentService.getCount(1));
        System.out.println(count);
    }

    /**
     * 添加评论
     */
    @Test
    public void testAddComment(){
        //"2020-09-17 17:50:05"
        commentService = BeanFactory.getInstance("CommentService", CommentServiceImpl.class);
        Integer result = commentService.addComment(new Comment(2,null,"这部电影好喜欢",2,null));
    }

    /**
     * 删除评论
     */
    @Test
    public void testDaleteComment(){
        commentService = BeanFactory.getInstance("CommentService", CommentServiceImpl.class);
        commentService.deleteComment(11);
    }

    @Test
    public void testGetCommentIdByParentId(){
        commentService = BeanFactory.getInstance("CommentService", CommentServiceImpl.class);
        List<Comment> commentsIdByParentId = commentService.getCommentsIdByParentId(3);
        for (Comment i : commentsIdByParentId){
            System.out.println(i.getComment_id());
        }
    }
    @Test
    public void testContent(){
        boolean b = SubstringContent("回复@lsx fsd  f");
        System.out.println(b);
    }

    @Test
    public void test(){
        UserDaoImpl userDao = BeanFactory.getInstance("UserDao",UserDaoImpl.class);
        System.out.println(userDao);

    }

    public boolean SubstringContent(String content){
        System.out.println("输出content:");
        if (content.contains("@")){
            String[] split = content.split(" ");
            if (split.length > 1){
                return true;
            }else return false;
        }else {
            if (content.equals("")){
                return false;
            }
        }
        return true;
    }
}
