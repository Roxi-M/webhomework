package servlet.message;

import activemq.Producerdao;
import activemq.impl.Producerdaoimpl;
import been.Message;
import been.User;
import dao.Messagedao;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.impl.Classdaoimpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


//发微博
public class SendMessage extends HttpServlet {
    private Messagedao ms;
    private Userdao userdao;
    private Producerdao producerdao;
    @Override
    public void init(){
        ms= Messagedaoimpl.getInstance();
        userdao= Userdaoimpl.getInstance();
        producerdao= Producerdaoimpl.getInstance();
        System.out.println("我好了");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession(false);
        User user=new User();
        user= (User) session.getAttribute("user");
        System.out.println(user.getAccount());  //检测是否登陆
        Message message=new Message();
        Date date=new Date();
        String context="context";
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload fileUpload=new ServletFileUpload(factory);
        fileUpload.setSizeMax(1024*1024*5);
        fileUpload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> list = fileUpload.parseRequest(request);
            Iterator<FileItem> iterator=list.iterator();
            while (iterator.hasNext()){
                FileItem fileItem=iterator.next();
                if(fileItem.isFormField()){
                    System.out.println(fileItem.getFieldName());
                    if(context.equals(fileItem.getFieldName())){
                        String  what= fileItem.getString("utf-8");
                        Classdaoimpl classdaoimpl=new Classdaoimpl();
                        String category=classdaoimpl.select_category(what);
                        System.out.println(what);
                        System.out.println(category);
                        message.setCategory(category);
                        message.setInfo(what);
                    }else {
                        response.sendError(1,"wrong");
                    }
                }
                // 这里只能上传一张图片 并保存 先暂时这样
                else if(!fileItem.isFormField()){
                    if(!fileItem.getName().equals("")) {
                        String name[] = user.getAccount().split("@"); // 得到该往哪里去存
                        String path = "D:\\weibo_winter"; //一个根目录
                        String suffx = fileItem.getName().substring(fileItem.getName().lastIndexOf("."));//得到后缀名
                        String filename =  date.getTime() + suffx; //真正地址
                        String photo_path="\\pitctures\\"+name[0]+"\\"+filename;
                        String savepath=path+photo_path;
                        File file = new File(savepath); //打开file
                        fileItem.write(file); //写入
                        message.setPhoto(photo_path); //存入message
                        fileItem.delete(); //删除临时文件*/
                    }
                }
                else {
                    response.sendRedirect("send.jsp");
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        message.setId(user.getId());
        ms.insert(message);
        String id= String.valueOf(user.getId());
        producerdao.send_queue(message.getInfo(),id);
        int msnum=userdao.update_messagenum(user);
        user.setMessagenum(msnum);
        session.setAttribute("user",user);
        response.setHeader("refresh","3;personPage.html");
        response.getWriter().write("留言成功,3s后自动跳转到个人信息页");
    }
    @Override
    public void destroy(){
        System.out.println("我死了");
    }
}
