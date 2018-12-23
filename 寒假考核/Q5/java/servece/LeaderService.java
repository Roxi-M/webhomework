package servece;

import java.sql.*;

public class LeaderService {
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
    private boolean addcots(String name){
        boolean flag=false;
        Connection con=getconnection();
        PreparedStatement ps=null;
        String sql="select * from leader where name=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  flag;
    }
    public String addwho(String name,String student,double addcots){
        Connection con=getconnection();
        PreparedStatement ps=null;
        if(addcots(name)){
            String sql="select * from student where name=?";
            try {
                ps=con.prepareStatement(sql);
                ps.setString(1,student);
                ResultSet resultSet=ps.executeQuery();
                if(resultSet.next()){
                    double cots=resultSet.getDouble("cots");
                    String sql1="update student set cots=? where name=?";
                    ps=con.prepareStatement(sql1);
                    double sum=cots+addcots;
                    ps.setDouble(1,sum);
                    ps.setString(2,student);
                    ps.executeUpdate();
                    String des="加了"+addcots+"分，现在为"+sum+"分";
                    return des;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "加分失败";
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
