package servlet.message;

import been.Message;
import been.User;
import dao.Messagedao;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import org.json.JSONObject;

import javax.servlet.ServletException;
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

@WebServlet("/tiaozhuan")
public class InfoMain extends HttpServlet {
    private Messagedao messagedao;
    private Userdao userdao;
    @Override
    public void init(){
        messagedao= Messagedaoimpl.getInstance();
        userdao= Userdaoimpl.getInstance();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession();
        String msnum= (String) session.getAttribute("msnum");
     //   String msnum=request.getParameter("msnum");
        Message message=new Message();
        System.out.println(msnum);
        int num= Integer.parseInt(msnum);
        message.setNum(num);
        message=messagedao.select_message(message);
        User user=userdao.select_nickname(message.getId());
        message.setNick_name(user.getNick_name());
        message.setPhoto_head(user.getHead());
        message=find_child(message);
        JSONObject jsonObject=new JSONObject(message);
        String json=jsonObject.toString();
        writer.write(json);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    //找的是 本微博的第一次回复
   public Message find_child(Message message){
        List<Message> messageList=messagedao.select_list(message);
        Iterator<Message> messageIterator=messageList.iterator();
        List<Message> list=new ArrayList<>();
        while (messageIterator.hasNext()){
           Message message1=messageIterator.next();
           User user=userdao.select_nickname(message1.getId());
            message1.setNick_name(user.getNick_name());
          message1.setPhoto_head(user.getHead());
           list.add( find_reply(message1));
        }
        message.setMessageList(list);
        return message;
    }

    //回复的回复
    public Message find_reply(Message message){
        List<Message> list= messagedao.select_list_reply(message);
        Iterator<Message> messageIterator=list.iterator();
        List<Message> messageList=new ArrayList<>(); //装messagechild
        while (messageIterator.hasNext()){
            Message message1=messageIterator.next();
            User user=userdao.select_nickname(message1.getId());
            message1.setNick_name(user.getNick_name());
            message1.setPhoto_head(user.getHead());
            messageList.add(find_reply(message1));
        }
        message.setMessageList(messageList);
        return message;
    }
}
