import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Login_Servlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        String usename=request.getParameter("usename");
        String password=request.getParameter("password");
        Main x=new Main();
        try {
            x.login(usename,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            if(x!=null) {
                response.getWriter().write("登陆成功");
            } else response.getWriter().write("账号或密码错误!");
    }
}
