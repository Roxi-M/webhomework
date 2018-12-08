import java.sql.*;

public class JDBCUtil {
    static{
        String driverName="com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getconnection(){
        Connection con=null;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket?serverTimezone=GMT%2B8","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  con;
    }
    public static void close(ResultSet resultSet,Connection connection,Statement statement){
        if(resultSet!=null){
            try {
                resultSet.close();
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
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
