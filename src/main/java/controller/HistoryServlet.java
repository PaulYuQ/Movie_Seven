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
@WebServlet(name = "HistoryServlet",urlPatterns = "*.histories")
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
        System.out.println(servletPath);
        /**
         * 对页面浏览记录的操作
         */
        if (servletPath.contains("doLoad")) {
            getUserId(request, response);
        }else if (servletPath.contains("doGetCount")){
            historyNumber(request, response);
        }else if (servletPath.contains("doShow")) {
            showHistoryList(request, response);
        }else if (servletPath.contains("doDelete")) {
            deleteHistoryByIds(request, response);
        }else if (servletPath.contains("doEmpty")) {
            emptyHistoryByUserId(request, response);
        }else if (servletPath.contains("doPage")) {
            pageHistory(request, response);
        }
        /**
         * 对Histories表操作
         */
        else if (servletPath.contains("doAddHistories")) {
            addHistories(request, response);
        }else if (servletPath.contains("doDeleteHistories")) {
            deleteHistories(request, response);
        }else if (servletPath.contains("doUpdateHistories")) {
            updateHistories(request, response);
        }else if (servletPath.contains("doFindHistoriy")) {
            findHistory(request, response);
        }else if (servletPath.contains("doListHistories")) {
            numberHistories(request, response);
        } else if (servletPath.contains("doListHistories")) {
            listHistories(request, response);
        }
    }

    /**
     * 对页面浏览记录的操作
     */


    /**
     * create by: sky
     * create time: 16:49 2020/9/16
     * 测试跳转
     * 设置一个session记录用户id
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void getUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(3);
        if(request.getParameter("id")==null) {
            return;
        }
        int id=Integer.parseInt(request.getParameter("id"));
        request.getSession().setAttribute("user_id",id);
        response.sendRedirect("history.jsp");
    }

    /**
     * create by: sky
     * create time: 9:06 2020/9/18
     * 分页按钮改变页数
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void pageHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page=(int)request.getSession().getAttribute("historyPage");
        int id=(int)request.getSession().getAttribute("user_id");
        if(("add").equals(request.getParameter("page"))){
            page=page+1;
        }else if(("delete").equals(request.getParameter("page"))){
            page=page-1;
        }else if(("final").equals(request.getParameter("page"))){
            page=(int)historyService.historyNumber(id)/6+1;
        }else {
            page=1;
        }
        response.getWriter().print(page);
        request.getSession().setAttribute("page",page);
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
        if (request.getSession().getAttribute("user_id") == null) {
            response.getWriter().print("false");
            return;
        }
        int id = (int) request.getSession().getAttribute("user_id");
        int page;
        if(request.getParameter("page")==null){
            page=1;
        }else {
            page=Integer.parseInt(request.getParameter("page"));
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
            historyService.movieDelete(Integer.parseInt(id));
        }
        response.getWriter().print("true");
    }

    /**
     * create by: sky
     * create time: 10:46 2020/9/18
     *
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void historyNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (int) request.getSession().getAttribute("user_id");
        response.getWriter().print(historyService.historyNumber(id));
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
        int id = (int) request.getSession().getAttribute("user_id");
        historyService.historyDelete(id);
        response.getWriter().print("true");
    }



    /**
     * 对Histories表操作
     */

    /**
     * create by: sky
     * create time: 20:15 2020/9/16
     * Histories表的增加，不输入history_id
     * 开头测试接收数据非空
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void addHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("user_id")==null||request.getParameter("movie_id")==null){
            return;
        }
        int userId=Integer.parseInt(request.getParameter("user_id"));
        int movieId=Integer.parseInt(request.getParameter("movie_id"));
        int progress=Integer.parseInt(request.getParameter("progress"));
        Histories histories=new Histories(userId,movieId,progress);
        historyService.movieAdd(histories);
    }


    /**
     * create by: sky
     * create time: 20:22 2020/9/16
     * Histories表的删除，根据history_id
     * 开头测试接收数据非空
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void deleteHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("history_id")==null){
            return;
        }
        int historyId=Integer.parseInt(request.getParameter("history_id"));
        historyService.movieDelete(historyId);
    }


    /**
     * create by: sky
     * create time: 20:27 2020/9/16
     * Histories表的修改
     * 开头测试接收数据非空
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void updateHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("user_id")==null||request.getParameter("movie_id")==null||request.getParameter("history_id")==null){
            return;
        }
        int userId=Integer.parseInt(request.getParameter("user_id"));
        int movieId=Integer.parseInt(request.getParameter("movie_id"));
        int progress=Integer.parseInt(request.getParameter("progress"));
        int historyId=Integer.parseInt(request.getParameter("history_id"));
        Histories histories=new Histories(userId,movieId,progress);
        histories.setHistory_id(historyId);
        historyService.movieUpdate(histories);
    }


    /**
     * create by: sky
     * create time: 20:28 2020/9/16
     * Histories表的单个查询，根据history_id
     * 开头测试接收数据非空
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void findHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("history_id")==null){
            return;
        }
        int historyId=Integer.parseInt(request.getParameter("history_id"));
        Histories histories=historyService.movieFindById(historyId);
    }


    /**
     * create by: sky
     * create time: 20:44 2020/9/16
     * 返回Histories表的行数
     * @Param: request
     * @Param: response
     * @return long
     */
    protected void numberHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print(historyService.movieNumber());
    }


    /**
     * create by: sky
     * create time: 20:39 2020/9/16
     * 实现分页展示 page :页数  row :一页展示的函数
     * @Param: request
     * @Param: response
     * @return void
     */
    protected void listHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page;
        if(request.getParameter("page")==null){
            page=1;
        }else {
            page=Integer.parseInt(request.getParameter("page"));
        }
        int row=6;
        List<Histories> list=historyService.movieList(page,row);
    }
}