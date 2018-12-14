import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
      static{
            String drivername="com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(drivername);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        public static Connection getConnection() throws SQLException {
            Connection con=null;
            con= DriverManager.getConnection("jdbc:mysql://localhost/roxi?serverTimezone=GMT%2B8","root","");
            return con;
        }
        public static void close(ResultSet resultSet,Statement statement,Connection connection){
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}
