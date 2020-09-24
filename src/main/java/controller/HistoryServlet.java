package controller;

import com.google.gson.Gson;
import factory.BeanFactory;
import pojo.Histories;
import pojo.ShowHistory;
import pojo.User;
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
@WebServlet(name = "HistoryServlet", urlPatterns = "*.histories")
public class HistoryServlet extends HttpServlet {
    private HistoryService historyService = BeanFactory.getInstance("HistoryService", HistoryService.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        /**
         * 对页面浏览记录的操作
         */
        if (servletPath.contains("doLoad")) {
            getUserId(request, response);
        } else if (servletPath.contains("doGetCount")) {
            historyNumber(request, response);
        } else if (servletPath.contains("doShow")) {
            showHistoryList(request, response);
        } else if (servletPath.contains("doDelete")) {
            deleteHistoryByIds(request, response);
        } else if (servletPath.contains("doEmpty")) {
            emptyHistoryByUserId(request, response);
        }
        /**
         * 对Histories表操作
         */
        else if (servletPath.contains("doAddHistories")) {
            addHistories(request, response);
        } else if (servletPath.contains("doDeleteHistories")) {
            deleteHistories(request, response);
        } else if (servletPath.contains("doUpdateHistories")) {
            updateHistories(request, response);
        } else if (servletPath.contains("doFindHistoriy")) {
            findHistory(request, response);
        } else if (servletPath.contains("doListHistories")) {
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
     * 测试跳转
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void getUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        request.getSession().setAttribute("user_id", id);
        response.sendRedirect("../../history.jsp");
    }


    /**
     * create by: sky
     * create time: 16:58 2020/9/16
     * 展示个人的浏览记录
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void showHistoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.getWriter().print("false");
            return;
        }
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getUser_id();
        int page;
        if (request.getParameter("page") == null) {
            page = 1;
        } else {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int ROW = 6;
        List<ShowHistory> list = historyService.historyList(id, page, ROW);
        if (list != null) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            response.getWriter().print(json);
        } else {
            response.getWriter().print("false");
        }
    }

    /**
     * create by: sky
     * create time: 17:00 2020/9/16
     * 删除浏览记录根据历史记录id
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void deleteHistoryByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String historyId = request.getParameter("id");
        String[] history = historyId.split(",");
        int i = 0;
        for (String id : history) {
            i = historyService.movieDelete(Integer.parseInt(id));
            if (i == 0) {
                break;
            }
        }
        if (i > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }


    /**
     * create by: sky
     * create time: 10:46 2020/9/18
     * 计算个人历史记录行数
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void historyNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.getWriter().print("false");
            return;
        }
//        int id = (int) request.getSession().getAttribute("user");
        User user = (User) request.getSession().getAttribute("user");
        response.getWriter().print(historyService.historyNumber(user.getUser_id()));
    }


    /**
     * create by: sky
     * create time: 19:24 2020/9/16
     * 根据用户id清空当前的历史记录
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void emptyHistoryByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getUser_id();
        int i = historyService.historyDelete(id);
        if (i > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }


    /**
     * 对Histories表操作
     */

    /**
     * create by: sky
     * create time: 20:15 2020/9/16
     * Histories表的增加，不输入history_id
     * 开头测试接收数据非空
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void addHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("user_id") == null || request.getParameter("movie_id") == null) {
            return;
        }
        int userId = Integer.parseInt(request.getParameter("user_id"));
        System.out.println("userID===" + userId);
        int movieId = Integer.parseInt(request.getParameter("movie_id"));
        System.out.println("movieId===" + movieId);
//        int progress = Integer.parseInt(request.getParameter("progress"));
        int progress = 1000;
        Histories histories = new Histories(userId, movieId, progress);
        int i = historyService.movieAdd(histories);
        if (i > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }


    /**
     * create by: sky
     * create time: 20:22 2020/9/16
     * Histories表的删除，根据history_id
     * 开头测试接收数据非空
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void deleteHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("history_id") == null) {
            return;
        }
        int historyId = Integer.parseInt(request.getParameter("history_id"));
        int i = historyService.movieDelete(historyId);
        if (i > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }


    /**
     * create by: sky
     * create time: 20:27 2020/9/16
     * Histories表的修改,根据history_id
     * 开头测试接收数据非空
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void updateHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("user_id") == null || request.getParameter("movie_id") == null || request.getParameter("history_id") == null) {
            return;
        }
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int movieId = Integer.parseInt(request.getParameter("movie_id"));
        int progress = Integer.parseInt(request.getParameter("progress"));
        int historyId = Integer.parseInt(request.getParameter("history_id"));
        Histories histories = new Histories(userId, movieId, progress);
        histories.setHistory_id(historyId);
        int i = historyService.movieUpdate(histories);
        if (i > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }


    /**
     * create by: sky
     * create time: 20:28 2020/9/16
     * Histories表的单个查询，根据history_id
     * 开头测试接收数据非空
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void findHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("history_id") == null) {
            return;
        }
        int historyId = Integer.parseInt(request.getParameter("history_id"));
        Histories histories = historyService.movieFindById(historyId);
        if (histories != null) {
            Gson gson = new Gson();
            String json = gson.toJson(histories);
            response.getWriter().print(json);
        } else {
            response.getWriter().print("false");
        }
    }


    /**
     * create by: sky
     * create time: 20:44 2020/9/16
     * 返回Histories表的行数
     *
     * @return long
     * @Param: request
     * @Param: response
     */
    protected void numberHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print(historyService.movieNumber());
    }


    /**
     * create by: sky
     * create time: 20:39 2020/9/16
     * 实现分页展示 page :页数  row :一页展示的函数
     *
     * @return void
     * @Param: request
     * @Param: response
     */
    protected void listHistories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page;
        if (request.getParameter("page") == null) {
            page = 1;
        } else {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int row = 6;
        List<Histories> list = historyService.movieList(page, row);
        if (list != null) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            response.getWriter().print(json);
        } else {
            response.getWriter().print("false");
        }
    }
}