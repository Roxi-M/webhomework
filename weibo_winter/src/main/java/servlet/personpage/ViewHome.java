package servlet.personpage;

import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/service/dohome")
public class ViewHome extends HttpServlet {
    private Userdao userdao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        HttpSession session=request.getSession();
        String msnum= (String) session.getAttribute("msnum");
        User user= (User) session.getAttribute("user");
        int flag=userdao.view_home(user, Integer.parseInt(msnum));
        if(flag==1){
            writer.write("1");
        }else if(flag==0){
            writer.write("0");
        }
    }
}
