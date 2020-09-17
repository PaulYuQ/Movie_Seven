package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import factory.BeanFactory;
import service.CollectionService;
import vo.CollectionVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 小强子大大
 */
@WebServlet(name = "CollectionServlet", urlPatterns = {"*.collection"})
public class CollectionServlet extends HttpServlet {
    private CollectionService collectionService;

    public CollectionServlet() {
        collectionService = BeanFactory.getInstance("CollectionService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        System.out.println(path);
        if(path.contains("queryAll.collection")) {
            queryAll(request, response);
        } else if(path.contains("delete.collection")) {
            delete(request, response);
        } else if(path.contains("queryAlllike.collection")) {
            queryAlllike(request, response);
        }
    }

    private void queryAlllike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String keyword = request.getParameter("keyword");
        String key = request.getParameter("key");
        String num = request.getParameter("num");
        if(num == null || num.equals("")) {
            num = "6";
        }
        if(key.equals("") || key == null || Integer.parseInt(key) < 0) {
            key = "0";
        }
        if(keyword.equals("")) {
            response.getWriter().print("没有找到收藏！");
        }
        List<CollectionVo> listLike = collectionService.getAllCollectionByKeyWord(keyword, 3, Integer.parseInt(key), Integer.parseInt(num));
        String json= JSON.toJSONString(listLike);
        response.getWriter().print(json);
        System.out.println(json);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String collectionId = request.getParameter("collectionId");
        int result = collectionService.delete(Integer.parseInt(collectionId));
        System.out.println(result);
        if(result > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("res", result);
            response.getWriter().print(jsonObject.toJSONString());
        } else {
            response.getWriter().print("该用户没有该收藏！");
        }
    }

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String key = request.getParameter("key");
        String num = request.getParameter("num");

        if(num == null || num.equals("")) {
            num = "6";
        }
        if(key.equals("") || key == null || Integer.parseInt(key) < 0) {
            key = "0";
        }

        List<CollectionVo> list = collectionService.getAllCollection(3, Integer.parseInt(key), Integer.parseInt(num));
        String json= JSON.toJSONString(list);
        response.getWriter().print(json);
        System.out.println(json);
    }
}