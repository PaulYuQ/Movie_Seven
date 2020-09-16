package controller;

import com.google.gson.Gson;
import factory.BeanFactory;
import pojo.Histories;
import pojo.ShowHistory;
import service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/9/14 14:26
 * @version: 1.0
 */
@WebServlet(name = "HistoryServlet",urlPatterns = {"*.histories"})
public class HistoryServlet extends HttpServlet {
    private HistoryService historyService= BeanFactory.getInstance("HistoryService", HistoryService.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
        this.doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.contains("doLoad")) {
            getUserId(request, response);
        }else if (servletPath.contains("doShow")) {
            showHistoryList(request, response);
        }else if (servletPath.contains("doDelete")) {
            deleteHistoryByIds(request, response);
        }else if (servletPath.contains("doEmpty")) {
            emptyHistoryByUserId(request, response);
        }
    }

    /**
     * create by: sky
     * create time: 16:49 2020/9/16
     * 设置一个session记录用户id
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void getUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("id")==null) {
            return;
        }
        int id=Integer.valueOf(request.getParameter("id"));
        request.getSession().setAttribute("userId",id);
        response.sendRedirect("history.html");
    }

    /**
     * create by: sky
     * create time: 16:58 2020/9/16
     * 展示个人的浏览记录
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void showHistoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userId")==null) {
            response.getWriter().print("false");
            return;
        }
        int id =(int)request.getSession().getAttribute("userId");
        int page;
        if(request.getParameter("page")==null) {
            page = 1;
        } else {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int ROW = 6;
        List<ShowHistory> list = historyService.historyList(id, page, ROW);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        response.getWriter().print(json);
    }

    /**
     * create by: sky
     * create time: 17:00 2020/9/16
     * 删除浏览记录根据历史记录id
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void deleteHistoryByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String historyId=request.getParameter("id");
        String[] history=historyId.split(",");
        for (String id:history){
            historyService.movieDelete(Integer.valueOf(id));
        }
        response.getWriter().print("true");
    }

    /**
     * create by: sky
     * create time: 19:24 2020/9/16
     * 根据用户id清空当前的历史记录
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void emptyHistoryByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object obj=request.getSession().getAttribute("userId");
        if(obj==null){
            response.getWriter().print("false");
            return;
        }
        historyService.historyDelete(Integer.valueOf((int)obj));
        response.getWriter().print("true");
    }

    /**
     * create by: sky
     * create time: 20:15 2020/9/16
     * Histories表的增加，不输入history_id
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void addHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("user_id")==null||request.getParameter("movie_id")==null
                ||request.getParameter("progress")==null){
            return;
        }
        int userId=Integer.valueOf(request.getParameter("user_id"));
        int movieId=Integer.valueOf(request.getParameter("movie_id"));
        int progress=Integer.valueOf(request.getParameter("progress"));
        Histories histories=new Histories(userId,movieId,progress);
        historyService.movieAdd(histories);
    }

    /**
     * create by: sky
     * create time: 20:22 2020/9/16
     * Histories表的删除，根据history_id
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void deleteHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("history_id")==null){
            return;
        }
        int historyId=Integer.valueOf(request.getParameter("history_id"));
        historyService.movieDelete(historyId);
    }

    /**
     * create by: sky
     * create time: 20:27 2020/9/16
     * Histories表的修改
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void updateHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("user_id")==null||request.getParameter("movie_id")==null
                ||request.getParameter("progress")==null||request.getParameter("history_id")==null){
            return;
        }
        int userId=Integer.valueOf(request.getParameter("user_id"));
        int movieId=Integer.valueOf(request.getParameter("movie_id"));
        int progress=Integer.valueOf(request.getParameter("progress"));
        int historyId=Integer.valueOf(request.getParameter("history_id"));
        Histories histories=new Histories(userId,movieId,progress);
        histories.setHistory_id(historyId);
        historyService.movieUpdate(histories);
    }

    /**
     * create by: sky
     * create time: 20:28 2020/9/16
     * Histories表的单个查询，根据history_id
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void findHistoryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("history_id")==null){
            return;
        }
        int historyId=Integer.valueOf(request.getParameter("history_id"));
        Histories histories=historyService.movieFindById(historyId);
    }

    protected void listHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}