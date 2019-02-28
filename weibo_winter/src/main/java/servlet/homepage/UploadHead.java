package servlet.homepage;
import been.User;
import dao.Userdao;
import dao.impl.Userdaoimpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet("/service/modify")
public class UploadHead extends HttpServlet {
    private Userdao userdao;

    @Override
    public void init() {
        userdao = Userdaoimpl.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String nick_name = "nick_name";
        String sex = "sex";
        String city = "city";
        String hobby = "hobby";
        String profile = "profile";
        String name[] = user.getAccount().split("@");
        String path = "D:\\weibo_winter\\";
        Date date = new Date();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        fileUpload.setSizeMax(4 * 1024 * 1024); //最多放4mb
        fileUpload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> fileItemList = fileUpload.parseRequest(request);
            Iterator<FileItem> itemIterator = fileItemList.iterator();
            while (itemIterator.hasNext()) {
                FileItem fileItem = itemIterator.next();
                if (fileItem.isFormField()) {
                    if (fileItem.getFieldName().equals(nick_name)) {
                        user.setNick_name(fileItem.getString("utf-8"));
                        userdao.update_nick(user);
                    } else if (fileItem.getFieldName().equals(sex)) {
                        user.setSex(fileItem.getString("utf-8"));
                        userdao.update_sex(user);
                    } else if (fileItem.getFieldName().equals(city)) {
                        user.setCity(fileItem.getString("utf-8"));
                        userdao.update_city(user);
                    } else if (fileItem.getFieldName().equals(hobby)) {
                        user.setHobby(fileItem.getString("utf-8"));
                        userdao.update_hobby(user);
                    } else if (fileItem.getFieldName().equals(profile)) {
                        user.setProfile(fileItem.getString("utf-8"));
                        userdao.update_profile(user);
                    }
                }else if(!fileItem.isFormField()){
                    if(!fileItem.getName().equals("")) {
                        String suxff = fileItem.getName().substring(fileItem.getName().lastIndexOf('.'));
                        String filename=date.getTime()+suxff;
                        String head_path="pitctures\\"+name[0]+"\\"+filename;
                        String savepath = path + head_path;
                        String deletepath=user.getHead();
                        File file=new File(path+deletepath);
                        if(file.exists()){
                            file.delete();
                        }
                        file = new File(savepath);
                        InputStream is=fileItem.getInputStream();
                        FileOutputStream fos=new FileOutputStream(file);
                        byte[] bytes=new byte[1024*8];
                        int len;
                        while ((len=is.read(bytes,0,bytes.length))!=-1){
                            fos.write(bytes,0,len);
                        }
                        fos.flush();
                        fos.close();
                     //   fileItem.write(file);
                        user.setHead(head_path);
                        userdao.update_head(user);
                        fileItem.delete();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("user",user);
        response.setHeader("refresh","3;/personPage.html");
        response.getWriter().write("请等待自动跳转.....");
    }
}
