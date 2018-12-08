import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class Main {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public Connection getConnection() {
        return connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public Statement getStatement() {
        return statement;
    }

    public Main(Connection con) {
        this.connection = con;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createtable() throws SQLException {
        String sql = "create table if not exists market(id int,name varchar(50),nums int,value double )";
        statement.addBatch(sql);
        statement.executeBatch();
    }
    public void insert(int id,String name,int nums,double value) throws SQLException {
        PreparedStatement insert=connection.prepareStatement("insert into market values (?,?,?,?)");
        insert.setInt(1,id);
        insert.setString(2,name);
        insert.setInt(3,nums);
        insert.setDouble(4,value);
        insert.addBatch();
        insert.executeBatch();
    }
    public void delete(int idt) throws SQLException {
        PreparedStatement delete=connection.prepareStatement("delete from market where id=?");
        delete.setInt(1,idt);
        delete.addBatch();
        delete.executeBatch();
    }
    public void updata(Double vl,String name) throws SQLException {
        PreparedStatement updata=connection.prepareStatement("updata market set value=? where name=?");
        updata.setDouble(1,vl);
        updata.setString(2,name);
        updata.addBatch();
        updata.executeBatch();
    }
    public void select() throws SQLException {
        String sql="select id,name,nums,value from market";
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println(resultSet.getString("id")+"\t"+resultSet.getString("name")+"\t"+resultSet.getString("nums")+"\t"+resultSet.getString("value"));
        }
    }
    public void clear() throws SQLException {
        String sql1="delete from market";
        statement.addBatch(sql1);
        statement.executeBatch();
    }
    public static void main(String args[]) {
        Connection con = JDBCUtil.getConnection();
        Main market = new Main(con);
        Scanner input=new Scanner(System.in);
        try {
            market.createtable();
            market.insert(5,"跳跳糖",3,3.7);
     //       market.delete(5);
            market.select();
            market.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(market.getResultSet(), market.getStatement(), market.getConnection());
        }
    }
}
