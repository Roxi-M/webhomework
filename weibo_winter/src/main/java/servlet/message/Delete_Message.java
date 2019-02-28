package servlet.message;

import been.Message;
import been.User;
import dao.Messagedao;
import dao.impl.Messagedaoimpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


// 有错误不敢用
@WebServlet("/service/delete")
public class Delete_Message extends HttpServlet {
    private Messagedao messagedao;
    @Override
    public void init(){
        messagedao=Messagedaoimpl.getInstance();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession(false);
        User user= (User) session.getAttribute("user");
        String msnum=request.getParameter("msnum");
        Message message=new Message();
        message.setNum(Integer.parseInt(msnum));
        messagedao.delete_message(user,message);
        find_child(message);
        writer.write("删除微博成功");
    }
    //找的是 本微博的第一次回复 进行删除
    public void find_child(Message message){
        List<Message> messageList=messagedao.select_list(message);
        Iterator<Message> messageIterator=messageList.iterator();
        while (messageIterator.hasNext()){
            Message message1=messageIterator.next();
            messagedao.delete_reply(message1);
            find_reply(message1);
        }
    }

    //回复的回复 进行删除
    public void find_reply(Message message){
        List<Message> list= messagedao.select_list_reply(message);
        Iterator<Message> messageIterator=list.iterator();
        while (messageIterator.hasNext()){
            Message message1=messageIterator.next();
            messagedao.delete_reply(message1);
            find_reply(message1);
        }
    }
}
