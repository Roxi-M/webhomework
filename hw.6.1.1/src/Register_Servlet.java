import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register_Servlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setCharacterEncoding("GBK");
    response.setCharacterEncoding("GBK");
    String usename = request.getParameter("usename");
    String pd = request.getParameter("pd");
    User user = new User();
    user.setName(usename);
    user.setPassword(pd);
    Main x = new Main();
    x.add(user);
    try {
      request.getRequestDispatcher("/login.jsp").forward(request, response);
    } catch (ServletException e) {
      e.printStackTrace();
    }
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("GBK");
    response.setCharacterEncoding("GBK");
    doPost(request, response);
  }
}