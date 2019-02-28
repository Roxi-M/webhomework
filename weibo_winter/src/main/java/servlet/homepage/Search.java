package servlet.homepage;

import been.Message;
import been.User;
import dao.Messagedao;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import org.json.JSONArray;
import org.json.JSONObject;
import service.impl.FindMessagedaoimpl;
import service.impl.UserServiceimpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Search extends HttpServlet {
    private Messagedao messagedao;
    private Userdao userdao;
    @Override
    public void init(){
        messagedao= Messagedaoimpl.getInstance();
        userdao= Userdaoimpl.getInstance();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        String search=request.getParameter("search");
        Message message=new Message();
        message.setInfo(search);
        List<Message> list=messagedao.select_list_message(message);
        FindMessagedaoimpl findMessagedaoimpl=new FindMessagedaoimpl();
        String json=findMessagedaoimpl.find_message_home(list);
        User user=new User();
        user.setNick_name(search);
        user.setTrue_name(search);
        user.setAccount(search);
        UserServiceimpl userServiceimpl=new UserServiceimpl();
        List<User> list1=userdao.select_user(user);
        JSONArray jsonArray=new JSONArray();
        for(User user1:list1){
            String json1=userServiceimpl.tojson(user1);
            JSONObject jsonObject=new JSONObject(json1);
            jsonArray.put(jsonObject);
        }
        String json1=jsonArray.toString();
        System.out.println(json);
        writer.write(json);
        writer.write(json1);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
}
