package controller.login;


import factory.BeanFactory;
import service.LoginService;
import service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet",urlPatterns = {"/login.users","/register.users"})
public class LoginServlet extends HttpServlet {
    private LoginService loginService;
    public LoginServlet(){
        loginService = BeanFactory.getInstance("LoginService", LoginServiceImpl.class);
    }

    public void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        String scode=(String)req.getSession().getAttribute("scode");
        String code=req.getParameter("code");
        if (loginService.login(name,pwd)){
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
    public void doRegister(HttpServletRequest req,HttpServletResponse resp){

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.contains("login.do")){
            doLogin(req,resp);
        }else if (path.contains("register.do")){
            doRegister(req,resp);
        }
    }
}
