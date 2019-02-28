package servlet.login;


import been.User;
import dao.impl.Userdaoimpl;
import service.impl.UserServiceimpl;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

public class Filter_login implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies=request.getCookies();
        HttpSession session=request.getSession();
        boolean flag=true;
        System.out.println(3);
        if(session.getAttribute("user")!=null){
            response.sendRedirect("logout.html");
        }else if(cookies!=null){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("user") ){
                        flag=false;
                        String account_password= URLDecoder.decode(cookie.getValue(),"GBK");
                        String user[]=account_password.split(",");
                        Userdaoimpl userdaoimpl=new Userdaoimpl();
                        User user1=new User();
                        user1.setAccount(user[0]);
                        user1.setPassword(user[1]);
                        user1=userdaoimpl.select_all(user1);
                        session.setAttribute("user",user1);
                        response.sendRedirect("logout.html");
                    }
                }
                if(flag) {
                    String account = request.getParameter("account");
                    String password = request.getParameter("password");
                    request.setAttribute("account", account);
                    request.setAttribute("password", password);
                    filterChain.doFilter(request, response);
                }
            }else {
            String account=request.getParameter("account");
            String password=request.getParameter("password");
            request.setAttribute("account",account);
            request.setAttribute("password",password);
            filterChain.doFilter(request,response);
        }
    }
    @Override
    public void destroy() {

    }
}
