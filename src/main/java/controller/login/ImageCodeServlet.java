package controller.login;

import cn.dsna.util.images.ValidateCode;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ImageCode",urlPatterns = {"/code"})
public class ImageCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 生成验证码
         */
        // 告诉客户端不使用缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setIntHeader("expires", 0);
        ValidateCode vc = new ValidateCode(110, 25, 4, 6);
        String scode = vc.getCode();
        request.getSession().setAttribute("scode", scode);
        vc.write(response.getOutputStream());
    }
}
