package controller;

import com.alibaba.fastjson.JSON;
import factory.BeanFactory;
import pojo.Comment;
import service.impl.CommentServiceImpl;
import util.GetPathUtil;
import util.SensitivewordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/14 20:14
 * @Version 1.0
 * 对评论的请求进行处理的控制器
 */
@WebServlet(name = "CommentServlet" ,urlPatterns = {"/comment/*"})
public class CommentServlet extends HttpServlet {
    /**
     * 定义业务层对象
     */
    private CommentServiceImpl commentService = null;

    public  CommentServlet(){
        /**
         * 初始化业务层对象
         */
        commentService = BeanFactory.getInstance("CommentService",CommentServiceImpl.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getRequestURI();
        if (servletPath.contains(GetPathUtil.countRequest)){
            doGetCommentCount(request,response);
        }else if(servletPath.contains(GetPathUtil.addCommentsRequest)){
            doAddComment(request,response);
        }else if (servletPath.contains(GetPathUtil.getAllCommentsRequest)){
            doGetAllComments(request,response);
        }else if(servletPath.contains(GetPathUtil.getDeleteCommentsRequest)){
            doDeleteComment(request,response);
        }
    }

    /**
     * 删除评论
     * @param request
     * @param response
     */
    private void doDeleteComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取评论id
        Integer id = Integer.valueOf(request.getParameter("comment_id"));
        List<Comment> comments = commentService.getCommentsIdByParentId(id);
        if (comments.size() == 0 || comments == null){
            commentService.deleteComment(id);
            response.getWriter().print("true");
        }else {
            for (Comment comment : comments){
                commentService.deleteComment(comment.getComment_id());
            }
            commentService.deleteComment(id);
            response.getWriter().print("true");
        }
    }
    /**
     * 获取 当前 电影id 的 所有 评论
     * @param request
     * @param response
     */
    private void doGetAllComments(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer movie_id = Integer.valueOf(request.getParameter("movie_id"));
        Integer flag = Integer.valueOf(request.getParameter("flag"));
        List<Comment> comments = commentService.getComments(movie_id,flag);
        if (comments != null){
            String string = JSON.toJSONString(comments);
            response.getWriter().print(string);
        }

    }

    /**
     * 获取 评论的总数
     * @param request
     * @param response
     */
    private void doGetCommentCount(HttpServletRequest request, HttpServletResponse response) {
        Integer movie_id = Integer.valueOf(request.getParameter("movie_id"));
        Integer count = commentService.getCount(movie_id);
        if (count > 0) {
            try {
                if (count > 0) {
                    response.getWriter().print(count);
                } else {
                    response.getWriter().print(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加评论
     * @param request
     * @param response
     * @throws IOException
     */
    private void doAddComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*{'movie_id':movie_id,'parent_id':-1,'content':content,'user_id':user_id,'date':date},*/
        SensitivewordUtil filter = new SensitivewordUtil();

        Integer movieId = Integer.valueOf(request.getParameter("movie_id"));

        Integer parentId = Integer.valueOf(request.getParameter("parent_id"));
        String content = request.getParameter("content");
        //判断获取的评论是否为空
        if (!substringContent(content)){
            System.out.println("评论信息为空！！");
            response.getWriter().print("你还没有评论！！");
        }else {
            //判断是否包含铭感词汇，
            boolean flag = filter.isContaintSensitiveWord(content, 1);
            if (flag){
                System.out.println("包含敏感词汇，同时返回到前端页面");
                response.getWriter().print("false");
            }else {
                Integer userId = Integer.valueOf(request.getParameter("user_id"));
                Integer i = 0;
                if (parentId== -1){
                    i = commentService.addComment(new Comment(movieId, null, content, userId, null));
                }else {
                    i = commentService.addComment(new Comment(movieId,parentId,content,userId,null));
                }
                if (i==1){ response.getWriter().print("true"); }else { response.getWriter().print("false"); }
            }
        }
    }
    /**
     * 判断评论是否为空
     * @param content
     * @return
     */
    public boolean substringContent(String content){
        System.out.println("输出content:"+content);
        if (content.equals("")){
            return false;
        }else {
            if (content.contains("@")){
                String[] split = content.split(" ");
                if (split.length == 1){
                    return false;
                }
            }
        }
        return true;
    }
}
