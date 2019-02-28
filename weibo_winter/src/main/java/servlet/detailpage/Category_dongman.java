package servlet.detailpage;

import been.Message;
import dao.Messagedao;
import dao.impl.Messagedaoimpl;
import service.impl.Classdaoimpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/category")
public class Category_dongman extends HttpServlet {
    @Override
    public void init(){
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        String category=request.getParameter("category");
        Message message=new Message();
        message.setCategory(category);
        Classdaoimpl classdaoimpl=new Classdaoimpl();
        String json=classdaoimpl.json(message);
        writer.write(json);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
}
