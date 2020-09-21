package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import factory.BeanFactory;
import pojo.Admin;
import service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        } else if (serlvetPath.contains("delete.admin")) {
            delete(req, resp);
            //查询总行数
        } else if (serlvetPath.contains("queryallcount.admin")) {
            queryAllCount(req, resp);
        } else if (serlvetPath.contains("login.admin")) {
            login(req, resp);
            //根据ID查询某个用户
        } else if (serlvetPath.contains("queryone.admin")) {
            queryOne(req, resp);
            //查询某页的集合
        }else if (serlvetPath.contains("querypage.admin")) {
            queryPage(req, resp);
        }
    }

    /**
     * 查询某页的内容
     * @param req
     * @param resp
     * @throws IOException
     */
    private void queryPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page=req.getParameter("page");
        String pageAmount=req.getParameter("pageAmount");
        List<Admin> allAdmin = adminService.findPageAdmin(Integer.parseInt(page),Integer.parseInt(pageAmount));
        if(allAdmin!=null) {
            JSONArray jsonArray=JSONArray.parseArray(JSON.toJSONString(allAdmin));
            resp.getWriter().print(jsonArray.toString());
            System.out.println(jsonArray.toString());
        }
    }

    /**
     * 查询某个管理员
     * @param req
     * @param resp
     * @throws IOException
     */
    private void queryOne(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id=req.getParameter("myid");
        Admin admin = adminService.findAdminById(Integer.parseInt(id));
        ArrayList<Admin> admins=new ArrayList<>();
        admins.add(admin);
        if(admin!=null) {
            String s = JSON.toJSONString(admins);
            resp.getWriter().print(s);
        }
    }

    /**
     * 登录使用
     * @param req
     * @param resp
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) {
        String name=req.getParameter("username");
        String password=req.getParameter("password");
        Admin admin=adminService.findAdminByNamePassword(name,password);
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

    /**
     * 查询总页数
     * @param req
     * @param resp
     * @throws IOException
     */
    private void queryAllCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       int pages= adminService.findAllCount();
       resp.getWriter().print(pages);
    }

    /**
     * 删除某个管理员
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id=req.getParameter("id");
        int i = adminService.deleteAdmin(Integer.parseInt(id));
        if(i==1){
            resp.getWriter().print("true");
        }else {
            resp.getWriter().print("false");
        }
    }

    /**
     * 更新某个管理员
     * @param req
     * @param resp
     */
    private void update(HttpServletRequest req, HttpServletResponse resp) {
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String phone=req.getParameter("phone");
        String control=req.getParameter("control");
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

    /**
     * 注册管理员
     * @param req
     * @param resp
     */
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
