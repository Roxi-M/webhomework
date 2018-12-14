import java.sql.*;

public class Main {
   Connection connection;
    ResultSet resultSet;
     Statement statement;

    public Connection getConnection() {
        return connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public Statement getStatement() {
        return statement;
    }
   public User login(String usename,String password) throws SQLException {
        User user=null;
           connection=JDBCUtil.getConnection();
           PreparedStatement con=connection.prepareStatement("select * from users where usename=? and password=? and state=?");
           con.setString(1,usename);
           con.setString(2,password);
           con.setInt(3,1);
           resultSet=con.executeQuery();
               if (resultSet.next()) {
                   user = new User();
                   user.setName(resultSet.getString("usename"));
                   user.setPassword(resultSet.getString("password"));
                   System.out.println("登陆成功");
               } else {
                   System.out.println("用户名或密码错误!");
               }

               JDBCUtil.close(resultSet, statement, connection);
       return user;
   }
    public void add(User user){
        Connection connection = null;
        try {
            connection=JDBCUtil.getConnection();
            String sql="insert into users(usename,password,state) values (?,?,?)";
            PreparedStatement con=connection.prepareStatement(sql);
            con.setString(1,user.getName());
            con.setString(2,user.getPassword());
            con.setInt(3,1);
            con.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(resultSet,statement,connection);
        }
    }
}

