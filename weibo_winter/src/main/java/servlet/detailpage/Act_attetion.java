package servlet.detailpage;

import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;
import service.impl.UserServiceimpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/service/action")
public class Act_attetion extends HttpServlet {
    private Userdao userdao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;chartset=utf-8");
        User user= (User) request.getSession(false).getAttribute("user");
        PrintWriter writer=response.getWriter();
        String who=request.getParameter("who");
        String right=request.getParameter("right");
        int otherid= Integer.parseInt(who);
        if(right.equals("0")){
            userdao.insert_fans(user,otherid);
            user.setAttention(user.getAttention()+1);
            request.getSession(false).setAttribute("user",user);
            writer.write("关注成功");
        }else {
            userdao.delete_fans(user,otherid);
            user.setAttention(user.getAttention()-1);
            request.getSession(false).setAttribute("user",user);
            writer.write("取消关注成功");
        }
    }
}
