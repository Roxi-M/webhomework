package servlet.enroll;

import been.User;
import dao.impl.Userdaoimpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Filter_enroll implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取参数
        String true_name=request.getParameter("true_name");
        String nick_name=request.getParameter("nick_name");
        String account=request.getParameter("account");
        String sex=request.getParameter("sex");
        String password=request.getParameter("password");
        User user=new User(true_name,account,password);
        user.setNick_name(nick_name);
        user.setSex(sex);
        System.out.println(account);
        PrintWriter writer=response.getWriter();
        Userdaoimpl userdaoimpl=new Userdaoimpl();
        int flag=userdaoimpl.select_account(account);
    //    System.out.println(flag);
        if(flag==1){
            writer.write("账号已经被注册");
        }else {
            request.setAttribute("users",user);
            request.setAttribute("flag",flag);
            System.out.println(account);
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
