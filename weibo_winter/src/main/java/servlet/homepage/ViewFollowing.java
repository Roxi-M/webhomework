package servlet.homepage;

import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;
import org.json.JSONObject;
import service.impl.UserServiceimpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ViewFollowing extends HttpServlet {
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
        User user=new User();
        user= (User) session.getAttribute("user");
        List list=new ArrayList();
        list=userdao.select_fans(user);
        UserServiceimpl userServiceimpl=new UserServiceimpl();
        //所有的关注本user 的following 展示
        String following  = following=userServiceimpl.select_user(list);
        writer.write(following);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
}
