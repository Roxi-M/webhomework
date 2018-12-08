import java.sql.*;

public class Main {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public Main(Connection con){
        this.connection=con;
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createtable1() throws SQLException {
        String ct="create table if not exists market(id varchar(130),cost char(30) ,manager varchar(30),tel varchar(30),address varchar(130))";
        statement.addBatch(ct);
        statement.executeBatch();
    }
    public void createtable2() throws SQLException{
        String ct="create table if not exists department(marketID char(30),id char(30) ,name char(30),position varchar(30),tel varchar(30),address varchar(30),age int)";
        statement.executeUpdate(ct);
    }
    public void createtable3() throws SQLException {
       String ct="create table if not exists shops(shoperID char ,marketID char,id char,name varchar(30) ,cost double ,price double )";
        statement.executeUpdate(ct);
    }
    public void createtable4() throws SQLException {
        String ct="create table if not exists shoper(name varchar(30) ,tel varchar(30) ,address varchar(30) )";
        statement.executeUpdate(ct);
    }
    public void createtable5() throws SQLException {
        String ct="create table if not exists hub(hubID char,hubadim varchar(30),tel varchar(30))";
        statement.executeUpdate(ct);
    }
    public void insert2(String imarketID,String id,String name,String position,String tel,String address,int age) throws SQLException {
        PreparedStatement con=connection.prepareStatement("insert into   department values (?,?,?,?,?,?,?)");
        con.setString(1,imarketID);
        con.setString(2,id);
        con.setString(3,name);
        con.setString(4,position);
        con.setString(5,tel);
        con.setString(6,address);
        con.setInt(7,age);
        con.executeUpdate();
    }
    public void insert1(String id,String cost,String manager,String tel,String address) throws SQLException {
        PreparedStatement con=connection.prepareStatement("insert into  market values (?,?,?,?,?)");
        con.setString(1,id);
        con.setString(2,cost);
        con.setString(3,manager);
        con.setString(4,tel);
        con.setString(5,address);
        con.addBatch();
        con.executeBatch();
    }
    public void insert3(String shoperID,String marketID,String id,String name,double cost,double price ) throws SQLException {
        PreparedStatement con=connection.prepareStatement("insert into shops values (?,?,?,?,?,?)");
        con.setString(1,shoperID);
        con.setString(2,marketID);
        con.setString(3,id);
        con.setString(4,name);
        con.setDouble(5,cost);
        con.setDouble(6,price);
        con.executeUpdate();
    }
    public void insert4(String name,String tel,String address) throws SQLException {
        PreparedStatement con=connection.prepareStatement("insert into shoper values (?,?,?)");
        con.setString(1,name);
        con.setString(2,tel);
        con.setString(3,address);
        con.executeUpdate();
    }
    public void insert5(String hubID,String hubadim,String tel) throws SQLException {
        PreparedStatement con=connection.prepareStatement("insert into hub values (?,?,?)");
        con.setString(1,hubID);
        con.setString(2,hubadim);
        con.setString(3,tel);
        con.executeUpdate();
    }
    public void selete1(String name) throws SQLException {
        String sql="select id ,cost,manager from market  where manager like ? order by cost,id desc ";
        PreparedStatement con=connection.prepareStatement(sql);
     con.setString(1,"%"+name+"%");
        resultSet=con.executeQuery();
        String id="id";
        while (resultSet.next()){
            System.out.println(resultSet.getString(id)+" "+resultSet.getString("cost")+" "+resultSet.getString("manager"));
        }
    }
    public void updata1_name(String name,String name1) throws SQLException {
        PreparedStatement con=connection.prepareStatement("update market set manager =? where manager =? ");
        con.setString(1,name);
        con.setString(2,name1);
        con.addBatch();
        con.executeBatch();
    }
    public void drop() throws SQLException {
        String drop="drop table  market";
        statement.executeUpdate(drop);
    }
    public static void main(String args[]){
        Connection con=JDBCUtil.getconnection();
        Main x=new Main(con);
      try {
            x.createtable1();
            x.createtable2();
            x.createtable3();
            x.createtable4();
            x.createtable5();
            x.insert1("1","402","张总","13032324051","充实的红岩生活");
          x.insert1("2","602","苏总","13042324051","充实的红岩生活");
          x.insert1("3","502","熊总","13032324351","充实的红岩生活");
          x.insert1("4","102","何总","13032364051","充实的红岩生活");
          x.updata1_name("何老板","何总");
            x.selete1("%");
           x.drop();
      } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.close(x.resultSet,x.connection,x.statement);
    }
}
