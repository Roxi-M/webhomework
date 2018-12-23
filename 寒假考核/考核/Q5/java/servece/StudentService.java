package servece;

import java.sql.*;

public class StudentService {
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
    public double buysomething(String name){
        Connection con=getconnection();
        String sql="select * from goods where name=?";
         double price = 0;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                price=resultSet.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
    public boolean isbuy(String name,String username){
        boolean flag=false;
        double price=buysomething(name);
        Connection con=getconnection();
        String sql="select * from student where name=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                double cots=resultSet.getDouble("cots");
                if(cots-price>=0){
                    flag=true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
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
