import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Find implements findimp{
    @Override
    public Student findname(String name) {
        Connection con=JDBCUtil.getconnection();
        PreparedStatement ps=null;
        Student student=null;
        String sql="select * from students where name like ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()){
              String name1=resultSet.getString("name");
               String stuid=resultSet.getString("stuid");
              String  clas=resultSet.getString("clas");
               String party=resultSet.getString("party");
                 student=new Student(name1,stuid,clas,party);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student findstuid(String stuid) {
        return null;
    }

    @Override
    public Student findclas(String clas) {
        return null;
    }

    @Override
    public Student findparty(String party) {
        return null;
    }
}

