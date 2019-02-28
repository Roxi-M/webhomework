package servlet.enroll;

import been.User;
import dao.impl.Userdaoimpl;
import dao.Userdao;
import service.impl.Sendmailimpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.lang.RandomStringUtils;

public class Registe1 extends HttpServlet {
    private Userdao userdao;
    @Override
    public void init(){
        userdao= Userdaoimpl.getInstance();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        User user= (User) request.getAttribute("users");
        int flag= (int) request.getAttribute("flag");
        String Acode=RandomStringUtils.randomAlphanumeric(10);
        user.setAcode(Acode);
        StringBuffer url = request.getRequestURL();
        String path= url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        //给 用户设置的邮箱 发邮件
        Sendmailimpl send=new Sendmailimpl(user,path);
        new Thread(send).start();
        // 数据库的处理  数据这里应该需要单例
        if(flag==-1) userdao.insert(user);
        else userdao.update_all_user(user);
        //相应responed 或者跳转
        //肯定要把数据转发给谁的 好进行验证
        response.setHeader("refresh","3;login.html");
        response.getWriter().write("请稍等邮件");
    }
    @Override
    public void destroy(){

    }
}
