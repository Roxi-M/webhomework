package servlet.message;

import been.Message;
import been.User;
import dao.impl.Messagedaoimpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/service/to123")
public class Reply_reply extends HttpServlet {
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
        System.out.println(reply);
        Messagedaoimpl messagedaoimpl=new Messagedaoimpl();
        messagedaoimpl.insert_reply_reply(user,message);
        System.out.println("恢复成功");
       // writer.write("回复成功");
    }
}
