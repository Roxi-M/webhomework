package servlet.enroll;

import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Registe2 extends HttpServlet {
    private Userdao userdao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer=response.getWriter();
        //这边收到数据后 进行处理 最后实现注册
        //登陆的过程就需要验证 state 是否为 1 是否为激活的
        String flag=request.getParameter("flag");
        String acount=request.getParameter("id");
        User user=new User(acount);
        String flag1=userdao.select_acode(user);
        if(flag1.equals(flag)){
            System.out.println( userdao.update(user));
            String name[]=acount.split("@");
            File file=new File("D:\\weibo_winter\\pitctures\\"+name[0]);
                if(!file.exists()){
                file.mkdir();
            }
            response.getWriter().write("激活成功");
        }
        else {
            response.getWriter().write("激活失败");
        }
    }
    @Override
    public void destroy(){

    }
}
