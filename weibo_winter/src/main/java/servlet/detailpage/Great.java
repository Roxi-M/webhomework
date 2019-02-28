package servlet.detailpage;

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

@WebServlet("/service/doGreat")
public class Great extends HttpServlet {
    private Messagedao messagedao;
    @Override
    public void init(){
        messagedao= Messagedaoimpl.getInstance();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        String msnum=request.getParameter("msnum");
        String great=request.getParameter("great");
        HttpSession session=request.getSession(false);
        User user=(User) session.getAttribute("user");
        Message message=new Message();
        message.setNum(Integer.parseInt(msnum));
        message=messagedao.select_message(message);
        System.out.println("3");
        //1为不点赞
        if(great.equals("0")){
            messagedao.insert_great(user,message);
       //  writer.write("点赞成功");
        }
        //0为点赞
        else if (great.equals("1")){
            messagedao.delete_great(user,message);
         //   writer.write("取消点赞");
        }else {
          //  writer.write("未知错误");
        }
    }
}
