package team.redrock.messageBoard.servlet;

import team.redrock.messageBoard.been.User;
import team.redrock.messageBoard.dao.impl.UserDaoimpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       request.setCharacterEncoding("utf-8");
       response.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDaoimpl userDaoimpl=new UserDaoimpl();
        user=userDaoimpl.select_user(user);
        if(user.getNickname()!=null){
            HttpSession session=request.getSession();
            session.setAttribute("users",user);
            response.getWriter().write("登陆成功，欢迎使用");
        }
        else {
            response.sendRedirect("index.html");
        }
    }
}
