package controller.login;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import factory.BeanFactory;
import pojo.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "UserServlet",urlPatterns = {"*.users"})
public class UserServlet extends HttpServlet {
    private UserService userService;
    public UserServlet(){
        userService = BeanFactory.getInstance("UserLoginService", UserService.class);
    }

    public void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String scode=(String)req.getSession().getAttribute("scode");
        String code=req.getParameter("code");
        PrintWriter out = resp.getWriter();
        if (userService.login(name,pwd)){
            if (code.equalsIgnoreCase(scode)){
                User user = userService.findByName(name);
                req.getSession().setAttribute("user",user);
//                resp.sendRedirect("index.jsp");
                out.println("<html>");
                out.println("<script>");
                out.println("window.open ('"+req.getContextPath()+"/index.jsp','_top')");
                out.println("</script>");
                out.println("</html>");
            }else {
                req.setAttribute("msg","验证码错误！");
//                req.getRequestDispatcher("loginAndRegister.jsp").forward(req, resp);
                out.println("<html>");
                out.println("<script>alert('验证码错误！')");
                out.println("window.open ('"+req.getContextPath()+"/loginAndRegister.jsp','_top')");
                out.println("</script>");
                out.println("</html>");
            }
        }else {
            req.setAttribute("msg","用户名或密码错误");
//            req.getRequestDispatcher("loginAndRegister.jsp").forward(req, resp);
            out.println("<html>");
            out.println("<script>alert('用户名或密码错误!')");
            out.println("window.open ('"+req.getContextPath()+"/loginAndRegister.jsp','_top')");
            out.println("</script>");
            out.println("</html>");
        }
    }

    public void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String pwd1 = req.getParameter("password1");
        String phone = req.getParameter("phone");
        JSONObject jsonObject = new JSONObject();
        if (!pwd.equals(pwd1) && userService.findByName(name) != null) {
            jsonObject.put("result", 4);
            resp.getWriter().print(jsonObject.toJSONString());
        } else if (pwd.equals(pwd1)) {
            if (userService.findByName(name) == null) {
                if (userService.addUser(name, pwd, phone)){
                    jsonObject.put("result", 1);
                    resp.getWriter().print(jsonObject.toJSONString());
                }else {
                    jsonObject.put("result", 5);
                    resp.getWriter().print(jsonObject.toJSONString());
                }
            } else {
                jsonObject.put("result", 2);
                resp.getWriter().print(jsonObject.toJSONString());
            }
        } else if (!pwd.equals(pwd1)) {
            jsonObject.put("result", 3);
            resp.getWriter().print(jsonObject.toJSONString());

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
    public void doLogout(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
            req.getSession().removeAttribute("user");
            resp.sendRedirect("index.jsp");
    }
    public void doUpdate(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.findByName("name");
        req.setAttribute("user",user);
        req.getRequestDispatcher("info.jsp").forward(req,resp);
    }
    public void doModify(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        User user;
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String phone = req.getParameter("phone");
        user=new User(id,name,pwd,phone);
        if (userService.updateUser(user)>0){
            req.getSession().setAttribute("user",user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",1);
            resp.getWriter().print(jsonObject.toJSONString());
        }
    }
    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Integer  id = Integer.parseInt(req.getParameter("id"));
        int res = userService.deleteById(id);
        if (res>0){

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",res);
            resp.getWriter().print(jsonObject.toJSONString());
        }
    }



    public void calCount(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        long count = userService.calCount();
        resp.getWriter().print((int)count);

    }
    public void queryPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        Integer page = Integer.parseInt(req.getParameter("page"));
        Integer pageAmount = Integer.parseInt(req.getParameter("pageAmount"));
        List<User> users =userService.findPageUsers(page,pageAmount);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(users));
        resp.getWriter().print(jsonArray.toString());
    }
    public void addUser(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        String name = req.getParameter("name");
        String password =req.getParameter("password");
        String phone = req.getParameter("phone");
        if (userService.addUser(name,password,phone)){
            resp.getWriter().print("true");
        }else {
            resp.getWriter().print("false");
        }
    }
    public void deleteById(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        Integer id = Integer.valueOf(req.getParameter("id"));
        if (userService.deleteById(id)>0){
            resp.getWriter().print("true");
        }else {
            resp.getWriter().print("false");
        }
    }
    public void updateUser(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        User user = null;
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String phone = req.getParameter("phone");
        user=new User(name,pwd,phone);
        if (userService.updateUser(user)>0){
            resp.getWriter().print("true");
        }else {
            resp.getWriter().print("false");
        }
    }
    public void searchById(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        Integer id = Integer.parseInt(req.getParameter("id"));
        User user = userService.findById(id);
        if(user!=null) {
            ArrayList<User> users=new ArrayList<>();
            users.add(user);
            String s = JSON.toJSONString(users);
            resp.getWriter().print(s);
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
        }else if (path.contains("queryUsers.users")){
            calCount(req,resp);
        }else if (path.contains("queryPage.users")){
            queryPage(req,resp);
        }else if (path.contains("addUser.users")){
            addUser(req,resp);
        }else if (path.contains("deleteById.users")){
            deleteById(req,resp);
        }else if (path.contains("updateUser.users")){
            updateUser(req,resp);
        }else if (path.contains("search.users")){
            searchById(req,resp);
        }
    }
}
