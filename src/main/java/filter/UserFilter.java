package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "UserFilter",urlPatterns = {"/info.jsp","/history.jsp","/collection.jsp"})
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user!=null){
            chain.doFilter(request,response);
        }else {
            httpServletResponse.setContentType("text/html; charset=utf-8");
            httpServletResponse.sendRedirect("loginAndRegister.jsp");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }
}
