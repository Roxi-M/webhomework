package servlet.homepage;

import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Update_nick_name extends HttpServlet {
    private Userdao userdao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        String nick=request.getParameter("nick_name");
        HttpSession session=request.getSession(false);
        User user= (User) session.getAttribute("user");
        user.setNick_name(nick);
        boolean flag=userdao.update_nick(user);
        String qizi= String.valueOf(flag);
        writer.write(qizi);
    }
}
