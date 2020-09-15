package controller.login;


import factory.BeanFactory;
import service.UserLoginService;
import service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UserLoginServlet",urlPatterns = {"/login.users","/register.users"})
public class UserLoginServlet extends HttpServlet {
    private UserLoginService userLoginService;
    public UserLoginServlet(){
        userLoginService = BeanFactory.getInstance("UserLoginService", LoginServiceImpl.class);
    }

    public void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String scode=(String)req.getSession().getAttribute("scode");
        String code=req.getParameter("code");
        if (userLoginService.login(name,pwd)){
            if (code.equalsIgnoreCase(scode)){
                resp.sendRedirect("welcome.jsp");
            }else {
                req.setAttribute("msg","验证码错误！");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }else {
            req.setAttribute("msg","用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
    public void doRegister(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String pwd1 = req.getParameter("password1");
        String phone = req.getParameter("phone");
        String scode=(String)req.getSession().getAttribute("scode");
        String code=req.getParameter("code");
            if (pwd.equals(pwd1)&&code.equalsIgnoreCase(scode)){
                if (userLoginService.doRegister(name,pwd,phone)){
                resp.getWriter().print("<script>alert('注册成功')</script>");
                req.getRequestDispatcher("register.jsp").forward(req,resp);
            }else{
                resp.getWriter().print("<script>alert('用户名已被占用')</script>");
                req.getRequestDispatcher("register.jsp").forward(req,resp);
            }
        }else if (!pwd.equals(pwd1)){
            req.setAttribute("msg","两次输入的密码不一致");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }else if (!code.equalsIgnoreCase(scode)){
            req.setAttribute("msg","验证码错误");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.contains("login.users")){
            doLogin(req,resp);
        }else if (path.contains("register.users")){
            doRegister(req,resp);
        }
    }
}