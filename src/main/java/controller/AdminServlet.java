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
import java.util.*;

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
        } else if (serlvetPath.contains("login.admin")) {
            login(req, resp);
        } else if (serlvetPath.contains("queryone.admin")) {
            queryone(req, resp);
        }
    }

    private void queryone(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id=req.getParameter("myid");
        Admin admin = adminService.findAdminById(Integer.parseInt(id));
        System.out.println(admin);
        ArrayList<Admin> admins=new ArrayList<>();
        admins.add(admin);
        if(admin!=null) {
            String s = JSON.toJSONString(admins);
            System.out.println(s);
            resp.getWriter().print(s);
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
                req.getRequestDispatcher("admin.jsp").forward(req,resp);
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

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id=req.getParameter("id");
        int i = adminService.deleteAdmin(Integer.parseInt(id));
        if(i==1){
            req.getRequestDispatcher("queryall.admin").forward(req,resp);
        }else {
            resp.getWriter().print("false");
        }
    }

    private void modifySuccess(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String phone=req.getParameter("phone");
        String control=req.getParameter("control");
        System.out.println(id+"|"+name+"|"+password+phone+control);
        int i = adminService.updateAdmin(new Admin(Integer.parseInt(id), name, password, phone, Integer.parseInt(control)));
        if(i==1){
            try {
                System.out.println("修改成功");
                resp.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                System.out.println("修改失败");
                resp.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) {
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String phone=req.getParameter("phone");
        int i=adminService.addAdmin(new Admin(name,password,phone));

        if(i==1){
            try {
                System.out.println("添加成功");
                resp.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                System.out.println("添加失败");
                resp.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
