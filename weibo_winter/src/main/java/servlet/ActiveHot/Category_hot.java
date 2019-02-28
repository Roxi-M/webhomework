package servlet.ActiveHot;

import been.Message;
import been.User;
import dao.Messagedao;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/later")
public class Category_hot extends HttpServlet {
    private Messagedao messagedao;
    private Userdao userdao;
    @Override
    public void init(){
        messagedao= Messagedaoimpl.getInstance();
        userdao= Userdaoimpl.getInstance();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        List<Message> messages=messagedao.select_alloff();
        JSONArray jsonArray=new JSONArray();
        for(int i=messages.size()-1;i>=0;i--){
                User user=userdao.select_nickname(messages.get(i).getId());
                messages.get(i).setNick_name(user.getNick_name());
                messages.get(i).setPhoto_head(user.getHead());
            JSONObject jsonObject=new JSONObject(messages.get(i));
            jsonArray.put(jsonObject);
        }
        writer.write(jsonArray.toString());
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
}
