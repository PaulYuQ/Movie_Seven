package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import factory.BeanFactory;
import pojo.Admin;
import service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 宋敏超
 * @version 1.0
 * @date 2020/9/15 10:52
 */
@WebServlet(name = "AdminServlet",urlPatterns = {"*.admin"})
public class AdminServlet extends HttpServlet {

    private AdminService adminService=null;

    public  AdminServlet(){
        adminService= BeanFactory.getInstance("AdminService",AdminService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String serlvetPath = req.getServletPath();
        if (serlvetPath.contains("insert.admin")) {
            register(req, resp);
        } else if (serlvetPath.contains("update.admin")) {
            update(req, resp);
        } else if (serlvetPath.contains("modify.admin")) {
            modifySuccess(req, resp);
        } else if (serlvetPath.contains("delete.admin")) {
            delete(req, resp);
        } else if (serlvetPath.contains("queryall.admin")) {
            queryall(req, resp);
        }else if (serlvetPath.contains("login.admin")) {
            login(req, resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        String name=req.getParameter("username");
        String password=req.getParameter("password");
        Admin admin=adminService.findAdminByNamePassword(name,password);
        System.out.println(admin);
        if(admin!=null){
            try {
                req.getSession().setAttribute("name",admin.getName());
                req.getSession().setAttribute("password",admin.getPassword());
                req.getSession().setAttribute("control",admin.getControl());
                req.getRequestDispatcher("admin.html").forward(req,resp);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }else {
            try {
                req.getRequestDispatcher("404.html").forward(req,resp);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }
    }

    private void queryall(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Admin> allAdmin = adminService.findAllAdmin();
        if(allAdmin!=null) {
            JSONArray jsonArray=JSONArray.parseArray(JSON.toJSONString(allAdmin));
            System.out.println(jsonArray.toString());
            resp.getWriter().print(jsonArray.toString());
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void modifySuccess(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) {
    }
}
