package servlet.login;

import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;
import service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

public class Login extends HttpServlet {
    private Userdao userdao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        String account= (String) request.getAttribute("account");;
        String password=(String) request.getAttribute("password");
        if(account==null||password==null){
            response.sendRedirect("index.jsp");
        }
        String right=request.getParameter("right");
        boolean flag= Boolean.parseBoolean(right);
        System.out.println(account+" "+password);
        User user=userdao.select_login(account,password);
        HttpSession session=request.getSession();
        if(user!=null){
            user=userdao.select_all(user);
            String profile=userdao.select_profile(user);
            user.setProfile(profile);
           if(user.getState()==1) {
               session.setAttribute("user", user);
               session.setMaxInactiveInterval(60 * 60 * 6);
               if (flag) {
                   String account_password = user.getId() + "," + password;
                   String code = URLEncoder.encode(account_password, "GBK");
                   System.out.println(code);
                   Cookie cookie = new Cookie("user", code);
                   cookie.setMaxAge(60 * 60 * 6);
                   cookie.setPath(request.getContextPath() + "/");
                   System.out.println(cookie.getValue());
                   response.addCookie(cookie);
               }
               response.sendRedirect("logout.html");
           }
            else if(user.getState()==2){
                response.setHeader("refresh","3;login.html");
                writer.write("未激活");
           }
        }else {
            response.setHeader("refresh","3;login.html");
            writer.write("用户名或密码错误");
        }
    }
}
