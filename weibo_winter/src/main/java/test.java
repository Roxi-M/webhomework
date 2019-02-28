import activemq.Consumerdao;
import activemq.Producerdao;
import activemq.impl.Consumerdaoimpl;
import activemq.impl.Producerdaoimpl;
import been.Message;
import been.User;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import org.json.JSONArray;
import org.json.JSONObject;
import service.impl.UserServiceimpl;

import javax.jms.JMSException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.util.JAXBSource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class test extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String x=request.getParameter("1");
        response.getWriter().write(x);
    }
}
