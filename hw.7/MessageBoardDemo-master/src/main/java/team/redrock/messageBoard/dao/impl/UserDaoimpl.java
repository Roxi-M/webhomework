package team.redrock.messageBoard.dao.impl;

import team.redrock.messageBoard.been.User;
import team.redrock.messageBoard.dao.UserDao;

import java.sql.*;

public class UserDaoimpl implements UserDao {
    private static final String drivername="com.mysql.cj.jdbc.Driver";
    private static final String root="root";
    private static final String pd="";
    private static final String db_url="jdbc:mysql://localhost:3306/roxi?characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
    static {
        try {
            Class.forName(drivername);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Connection getConnection() {
        Connection con=null;
        try {
            con= DriverManager.getConnection(db_url,root,pd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    @Override
    public void insert_user(User user) {
        boolean flag=false;
        Connection connection=getConnection();
           PreparedStatement ps=null;
           String sql="insert into users(nickname,username,password) values (?,?,?)";
        try {
            ps=connection.prepareStatement(sql);
        //     ps.setInt(1,user.getId());
             ps.setString(1,user.getNickname());
             ps.setString(2,user.getUsername());
             ps.setString(3,user.getPassword());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User select_user(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="select * from users where username=? and password=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                user.setNickname(resultSet.getString("nickname"));
                user.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return user;
    }
     private  void close(ResultSet resultSet, Connection connection, Statement statement){
        try {
            resultSet.close();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
