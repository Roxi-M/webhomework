package servlet.homepage;

import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;
import service.UserService;
import service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class HomePage extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession(false);
        User user= (User) session.getAttribute("user");
        request.setAttribute("person",user);
        UserServiceimpl userServiceimpl=new UserServiceimpl();
        String useinfo=userServiceimpl.tojson(user);
        System.out.println(useinfo);
        writer.write(useinfo);
    }
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
