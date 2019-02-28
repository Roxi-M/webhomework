package servlet.logout;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Logout extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        String right=request.getParameter("right");;
        HttpSession session=request.getSession();
        Cookie[] cookies=request.getCookies();
        if(session!=null && right.equals("点击退出")){
            if(session.getAttribute("user")!=null) {
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("user")) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
                session.removeAttribute("user");
                response.sendRedirect("login.html");
            }else {
                session.invalidate();
                writer.write("未知按钮");
            }
        }
        else {
            writer.write("请先登陆");
        }
    }

}
