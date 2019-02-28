package servlet.message;

import been.Message;
import been.User;
import dao.impl.Messagedaoimpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Reply extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        String reply=request.getParameter("context");
        String msnum=request.getParameter("num");
        HttpSession session=request.getSession(false);
        User user= (User) session.getAttribute("user");
        Message message=new Message();
        message.setFather(Integer.parseInt(msnum));
        message.setInfo(reply);
        Messagedaoimpl messagedaoimpl=new Messagedaoimpl();
        messagedaoimpl.insert_reply(user,message);
        System.out.println("回复成功");
     //   writer.write("回复成功");
    }
}
