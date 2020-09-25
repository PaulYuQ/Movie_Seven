package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import factory.BeanFactory;
import pojo.Collection;
import pojo.User;
import service.CollectionService;
import service.impl.CollectionServiceImpl;
import vo.CollectionVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小强子大大
 */
@WebServlet(name = "CollectionServlet", urlPatterns = {"*.collection"})
public class CollectionServlet extends HttpServlet {
    private CollectionService collectionService;

    public CollectionServlet() {
        collectionService = BeanFactory.getInstance("CollectionService", CollectionServiceImpl.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        if(path.contains("queryAll.collection")) {
            queryAll(request, response);
        } else if(path.contains("delete.collection")) {
            delete(request, response);
        } else if(path.contains("queryAlllike.collection")) {
            queryAlllike(request, response);
        } else if(path.contains("queryCollectionCount.collection")) {
            queryCollectionCount(request, response);
        } else if(path.contains("queryCollectionList.collection")) {
            queryCollectionList(request, response);
        } else if(path.contains("addCollection.collection")) {
            addCollection(request, response);
        } else if(path.contains("deleteCollection.collection")) {
            deleteCollection(request, response);
        } else if(path.contains("updateCollection.collection")) {
            updateCollection(request, response);
        } else if(path.contains("queryCollectionById.collection")) {
            queryCollectionById(request, response);
        } else if(path.contains("deleteInPlayer.collection")) {
            deleteInPlayer(request, response);
        } else if(path.contains("queryCollectionStatus.collection")) {
            queryCollectionStatus(request, response);
        } else if(path.contains("getCollectionCountByUserId.collection")) {
            getCollectionCountByUserId(request, response);
        } else if(path.contains("getCollectionCountByKeyWord.collection")) {
            getCollectionCountByKeyWord(request, response);
        }
    }

    private void getCollectionCountByKeyWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUser_id();
        String keyword = request.getParameter("keyword");
        long result = collectionService.getCollectionCountByKeyWord(userId, keyword);
        response.getWriter().print(result);
    }

    private void getCollectionCountByUserId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUser_id();
        long result = 0;
        result = collectionService.getCollectionCountByUserId(userId);

        response.getWriter().print(result);
    }


    private void queryCollectionStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String movieId = request.getParameter("movieId");
        int result = 0;
        if(!"".equals(userId)) {
            result = collectionService.queryCollectionStatus(Integer.parseInt(userId), Integer.parseInt(movieId));
        }
        if(result > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }

    private void deleteInPlayer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String movieId = request.getParameter("movieId");
        int result = 0;
        if(!"".equals(userId)) {
            result = collectionService.deleteInPlayer(Integer.parseInt(userId), Integer.parseInt(movieId));
        }
        if(result > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }

    private void queryCollectionById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String collectionId = request.getParameter("collectionId");
        Collection collectionResult= collectionService.getCollectById(Integer.parseInt(collectionId));
        if(collectionResult != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("collectionResult", collectionResult);
            response.getWriter().print(jsonObject.toJSONString());
        } else {
            response.getWriter().print("没有查询到该收藏！");
        }
    }

    private void updateCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String movieId = request.getParameter("movieId");
        Collection collection = new Collection();
        collection.setUser_id(Integer.parseInt(userId));
        collection.setMovie_id(Integer.parseInt(movieId));
        int insertResult = 0;
        if(!"".equals(userId)) {
            insertResult = collectionService.update(collection);
        }
        if(insertResult > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }

    private void deleteCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String collectionId = request.getParameter("collectionId");
        int deleteResult = collectionService.delete(Integer.parseInt(collectionId));
        if(deleteResult > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }

    private void addCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String movieId = request.getParameter("movieId");
        Collection collection = new Collection();
        collection.setUser_id(Integer.parseInt(userId));
        collection.setMovie_id(Integer.parseInt(movieId));
        int insertResult = collectionService.insert(collection);
        if(insertResult > 0) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }


    private void queryCollectionList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = request.getParameter("page");
        String pageAmount = request.getParameter("pageAmount");
        List<Collection> userCollectionByPage = collectionService.getUserCollectionByPage(Integer.parseInt(page), Integer.parseInt(pageAmount));
        if(userCollectionByPage != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("collectionList", userCollectionByPage);
            response.getWriter().print(jsonObject.toJSONString());
        }
    }

    private void queryCollectionCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Long aLong = collectionService.calCollectionCount();
        if(aLong > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("collectionCount", aLong);
            response.getWriter().print(jsonObject.toJSONString());
        }
    }

    private void queryAlllike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");
        String page = request.getParameter("page");
        //String userId = request.getAttribute()
        User user = (User) request.getSession().getAttribute("user");
        int user_id = user.getUser_id();

        if(keyword.equals("")) {
            response.getWriter().print("没有找到收藏！");
        }
        List<CollectionVo> listLike = collectionService.getAllCollectionByKeyWord(keyword, user_id, Integer.parseInt(page), 6);
        String json= JSON.toJSONString(listLike);
        response.getWriter().print(json);

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String collectionId = request.getParameter("collectionId");
        int result = collectionService.delete(Integer.parseInt(collectionId));

        if(result > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("res", result);
            response.getWriter().print(jsonObject.toJSONString());
        } else {
            response.getWriter().print("该用户没有该收藏！");
        }
    }

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = request.getParameter("page");
        //String userId = request.getAttribute()
        User user = (User) request.getSession().getAttribute("user");
        int user_id = user.getUser_id();
        if(page.equals("") || page == null || Integer.parseInt(page) < 0) {
            page = "1";
        }

        List<CollectionVo> list = collectionService.getAllCollection(user_id, Integer.parseInt(page), 6);
        String json= JSON.toJSONString(list);
        response.getWriter().print(json);

    }
}
