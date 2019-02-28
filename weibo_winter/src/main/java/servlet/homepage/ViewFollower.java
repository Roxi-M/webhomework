package servlet.homepage;

import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;
import service.impl.UserServiceimpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewFollower extends HttpServlet {
    private Userdao userdao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession(false);
        User user= (User) session.getAttribute("user");
        List list=userdao.select_attention(user);
        UserServiceimpl userServiceimpl=new UserServiceimpl();
        //user 关注的所有人人
        String following=userServiceimpl.select_user(list);
        writer.write(following);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
}
