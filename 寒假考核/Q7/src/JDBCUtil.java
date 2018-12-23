import java.sql.*;

public class JDBCUtil {
    static {
        try {
            String driveName="com.mysql.cj.jdbc.Driver";
            Class.forName(driveName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getconnection(){
        Connection con=null;
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost/roxi"+"? serverTimezone=GMT%2B8","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void close(ResultSet resultSet, Connection connection, Statement statement) throws SQLException {
        if(statement!=null){
            statement.close();}
        if(connection!=null){
            connection.close();}
        if(statement!=null){
            statement.close();}
    }
}
