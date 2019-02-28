package servlet.homepage;

import been.Message;
import been.User;
import dao.Messagedao;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import service.impl.FindMessagedaoimpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Home_info extends HttpServlet {
    private Userdao userdao;
    private Messagedao messagedao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
        messagedao= Messagedaoimpl.getInstance();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession(false);
        User user=(User) session.getAttribute("user");
        List<Message> list= userdao.select_home(user);
        // 现在开始 搜索list 所有的message json 组装过去 用到Service 服务的find message
        FindMessagedaoimpl findMessagedaoimpl=new FindMessagedaoimpl();
        String json=findMessagedaoimpl.find_message_home(list);
        writer.write(json);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
}
