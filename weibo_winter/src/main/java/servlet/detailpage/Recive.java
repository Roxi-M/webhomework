package servlet.detailpage;

import activemq.Consumerdao;
import activemq.impl.Consumerdaoimpl;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@WebServlet("/service/recive")
public class Recive extends HttpServlet {
    private Userdao userdao;
    private Messagedao messagedao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
        messagedao= Messagedaoimpl.getInstance();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        List<Integer> list=userdao.select_attention(user); //得到关注用户的id
        Iterator iterator=list.iterator();
        JSONArray jsonArray=new JSONArray();
        Consumerdaoimpl  consumerdaoimpl=new Consumerdaoimpl();
        while (iterator.hasNext()) {
            String id= String.valueOf(iterator.next());
            List<String> stringList=consumerdaoimpl.recevie(id);
            if(stringList!=null){
                for(int i=0;i<stringList.size();i++){
                    Message message=messagedao.select_message(stringList.get(i),id);
                    JSONObject jsonObject=new JSONObject(message);
                    jsonArray.put(jsonObject);
                }
            }
        }
        writer.write(jsonArray.toString());
    }
}
