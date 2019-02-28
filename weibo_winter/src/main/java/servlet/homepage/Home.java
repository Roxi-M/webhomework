package servlet.homepage;

import been.Message;
import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Home extends HttpServlet {
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
        HttpSession session=request.getSession(false);
        User user= (User) session.getAttribute("user");
        String msnum=request.getParameter("msnum");
        String right=request.getParameter("right");
        if(right.equals("0")) {
            userdao.insert_home(user, Integer.parseInt(msnum));
            user.setHouse(user.getHouse()+1);
            session.setAttribute("user",user);
            writer.write("收藏成功");
        }
        else if(right.equals("1")){
            userdao.delete_home(user, Integer.parseInt(msnum));
            user.setHouse(user.getHouse()-1);
            session.setAttribute("user",user);
            writer.write("取消收藏");
        }
    }
}
