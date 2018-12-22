package team.redrock.messageBoard.servlet;

import com.sun.deploy.net.HttpRequest;
import team.redrock.messageBoard.been.User;
import team.redrock.messageBoard.dao.impl.UserDaoimpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registe")
public class RegistServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String nickname = request.getParameter("nickname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(nickname, username, password);
        UserDaoimpl userDaoimpl = new UserDaoimpl();
        userDaoimpl.insert_user(user);
        response.getWriter().write("注册成功");
    }
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
