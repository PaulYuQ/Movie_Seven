package controller.login;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import factory.BeanFactory;
import pojo.User;
import service.UserLoginService;
import service.impl.UserLoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "UserLoginServlet",urlPatterns = {"*.users"})
public class UserLoginServlet extends HttpServlet {
    private UserLoginService userLoginService;
    public UserLoginServlet(){
        userLoginService = BeanFactory.getInstance("UserLoginService", UserLoginServiceImpl.class);
    }

    public void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String scode=(String)req.getSession().getAttribute("scode");
        String code=req.getParameter("code");
        if (userLoginService.login(name,pwd)){
            if (code.equalsIgnoreCase(scode)){
                User user = userLoginService.findByName(name);
                req.getSession().setAttribute("user",user);
                resp.sendRedirect("index.jsp");
            }else {
                req.setAttribute("msg","验证码错误！");
                req.getRequestDispatcher("loginAndRegister.jsp").forward(req, resp);
            }
        }else {
            req.setAttribute("msg","用户名或密码错误");
            req.getRequestDispatcher("loginAndRegister.jsp").forward(req, resp);
        }
    }
    public void doRegister(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String pwd1 = req.getParameter("password1");
        String phone = req.getParameter("phone");
    /*    String scode=(String)req.getSession().getAttribute("scode");

        String code=req.getParameter("code");*/
            if (pwd.equals(pwd1)){
                if (userLoginService.findByName(name)==null){
                    userLoginService.addUser(name,pwd,phone);
                    out.print("<script>alert('注册成功'); window.location='loginAndRegister.jsp' </script>");
                } else{
                resp.getWriter().print("<script>alert('用户名已被占用');window.location='loginAndRegister.jsp'</script>");
            }
        }else if (!pwd.equals(pwd1)){
            req.setAttribute("msg1","两次输入的密码不一致");
            req.getRequestDispatcher("loginAndRegister.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
    public void doLogout(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
            req.getSession().removeAttribute("user");
            resp.sendRedirect("loginAndRegister.jsp");
    }
    public void doUpdate(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        User user = userLoginService.findByName("name");
        req.setAttribute("user",user);
        req.getRequestDispatcher("info.jsp").forward(req,resp);
    }
    public void doModify(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        User user;
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String phone = req.getParameter("phone");
        user=new User(name,pwd,phone);
        if (userLoginService.updateUser(user)>0){
            req.getSession().setAttribute("user",user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",1);
            resp.getWriter().print(jsonObject.toJSONString());
        }
    }
    public void doDelete(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
        int res = userLoginService.deleteByName(name);
        if (res>0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",res);
            resp.getWriter().print(jsonObject.toJSONString());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.contains("login.users")){
            doLogin(req,resp);
        }else if (path.contains("register.users")){
            doRegister(req,resp);
        }else if (path.contains("logout.users")){
            doLogout(req,resp);
        }else if (path.contains("update.users")){
            doUpdate(req,resp);
        }else if (path.contains("modify.users")){
            doModify(req,resp);
        }else if (path.contains("delete.users")){
            doDelete(req,resp);
        }
    }
}
