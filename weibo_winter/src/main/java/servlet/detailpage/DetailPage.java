package servlet.detailpage;

import been.Message;
import dao.Messagedao;
import dao.impl.Messagedaoimpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DetailPage extends HttpServlet {
    private Messagedao messagedao;
    @Override
    public void init(){
        messagedao= Messagedaoimpl.getInstance();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        String msnum=request.getParameter("msnum");
        Message message=new Message();
        message.setNum(Integer.parseInt(msnum));
        message=messagedao.select_message(message); //找到这个发微博的主体
        //开始找他的评论
        String json;
    }
}
