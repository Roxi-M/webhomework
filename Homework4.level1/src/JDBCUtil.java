import java.sql.*;

public class JDBCUtil {
    static{
        String drivername="com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(drivername);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection con=null;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/roxi?serverTimezone=GMT%2B8","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
