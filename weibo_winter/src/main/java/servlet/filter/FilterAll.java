package servlet.filter;

import been.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FilterAll implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("我本来就是在启动前开始");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //检测有无登陆 需要用到service 服务 的时候 都需要用到这个过滤器
        HttpSession session=request.getSession(false);
        if(session!=null){
            User user=(User) session.getAttribute("user");
            if(user!=null) {
                filterChain.doFilter(request, response);
            }else {

                response.getWriter().write("请登录再那个");
            }
        }else {
            response.getWriter().write("登陆后才能操作");
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤得满意吗");
    }
}
