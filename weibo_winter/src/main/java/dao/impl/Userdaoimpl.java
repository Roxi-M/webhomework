package dao.impl;

import been.Message;
import been.User;
import dao.Userdao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Userdaoimpl implements Userdao {
    private static final String DRIVERNAME="com.mysql.cj.jdbc.Driver";
    private static final String DB_url="jdbc:mysql://localhost:3306/weibo?serverTimezone=UTC";
    private static final String USER="root";
    private static final String PASSWORD="";
    //单例模式 防止 第一次 创建用户的？？？  百度下单例模式吧。。吹不出来
    public static Userdao instance=null;
    public static Userdao getInstance(){
        if(instance==null){
            synchronized (Userdao.class){
                if(instance==null){
                    instance=new Userdaoimpl();
                }
            }
        }
        return instance;
    }
    static {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        Connection con=null;
        try {
            con= DriverManager.getConnection(DB_url,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // 注册用的insert 把数据 添加到数据库 实现用户注册
    @Override
    public String insert(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="insert into users(true_name,nick_name,account,fans,attention,messagenum,acode,state,sex,house) values (?,?,?,?,?,?,?,?,?,?)";
        String sql1="insert into password values (?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getTrue_name());
            ps.setString(2,user.getNick_name());
            ps.setString(3,user.getAccount());
            ps.setInt(4,0);
            ps.setInt(5,0);
            ps.setInt(6,0);
            ps.setString(7,user.getAcode());
            ps.setInt(8,2);
            ps.setString(9,user.getSex());
            ps.setInt(10,0);
            ps.execute();
            ps=con.prepareStatement(sql1);
            ps.setString(1,user.getAccount());
            ps.setString(2,user.getPassword());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "{\"state\":10000}";
    }

    @Override
    public void update_all_user(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="delete from users where account=?";
        String sqlt="delete from password where user=?";
        String sql1="insert into users(true_name,nick_name,account,fans,attention,messagenum,acode,state,sex,house) values (?,?,?,?,?,?,?,?,?,?)";
        String sql2="insert into password values (?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getAccount());
            ps.execute();
            ps=con.prepareStatement(sqlt);
            ps.setString(1,user.getAccount());
            ps.execute();
            ps=con.prepareStatement(sql1);
            ps.setString(1,user.getTrue_name());
            ps.setString(2,user.getNick_name());
            ps.setString(3,user.getAccount());
            ps.setInt(4,0);
            ps.setInt(5,0);
            ps.setInt(6,0);
            ps.setString(7,user.getAcode());
            ps.setInt(8,0);
            ps.setString(9,user.getSex());
            ps.setInt(10,0);
            ps.execute();
            ps=con.prepareStatement(sql2);
            ps.setString(1,user.getAccount());
            ps.setString(2,user.getPassword());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //用户修改头像
    public User updata_head(User user){
        Connection con=getConnection();
        PreparedStatement st=null;
        String sql="update users set head=? where account=?";
        try {
            st=con.prepareStatement(sql);
            st.setString(1,user.getHead());
            st.setString(2,user.getAccount());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    //激活账户的update
    @Override
    public String update(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="update users set state=1 where account=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getAccount());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  "sucess";
    }

    @Override
    public User select_nickname(int id) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="select nick_name,head from users where id=?";
        ResultSet rs=null;
        User user=new User();
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if(rs.next()){
                 user.setNick_name(  rs.getString(1));
                 user.setHead(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  user;
    }

    //查找acode
    @Override
    public String select_acode(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String flag=null;
        String sql="select * from users where account=?";
        ResultSet rs=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getAccount());
             rs=ps.executeQuery();
            if(rs.next()){
                flag=rs.getString("acode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                con.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
    //搜索用户所有信息(除开密码) 使用id 搜索
    public User select_all(User user){
        Connection con=getConnection();
        PreparedStatement ps=null;
        User user1=new User();
        String sql="select * from users where id=?";
        ResultSet rs=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
             rs=ps.executeQuery();
            if(rs.next()){
                user1.setId(rs.getInt(1));
                user1.setTrue_name(rs.getString(2));
                user1.setNick_name(rs.getString(3));
                user1.setAccount(rs.getString(4));
                user1.setSex(rs.getString(5));
                user1.setHobby(rs.getString(6));
                user1.setCity(rs.getString(7));
                user1.setHead(rs.getString(8));
                user1.setFollows(rs.getInt(9));
                user1.setAttention(rs.getInt(10));
                user1.setMessagenum(rs.getInt(11));
                user1.setState(rs.getInt(13));
                user1.setHouse(rs.getInt(14));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                con.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user1;
    }
    //验证密码登陆
    public boolean select_pa(User user){
        Connection con=getConnection();
        String sql="select * from password where user=?";
        Statement st=null;
        ResultSet rs=null;
        try {
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.getString(2).equals(user.getPassword())){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //检验此账号有无被注册过
    public int select_account(String account){
        Connection con=getConnection();
        String sql="select state from users where account=? ";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,account);
            rs=ps.executeQuery();
            if(rs.next()){
                if(rs.getInt(1)==0){
                    return 0;
                }else {
                    return 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                con.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  -1;
    }
    //登陆验证
    @Override
    public User select_login(String account1,String password) {
        Connection con=getConnection();
        Statement statement=null;
        PreparedStatement ps=null;
        String sql="select id,account from users ";
        String sql1="select * from password where user=?";
        ResultSet resultSet=null;
        User user1=new User();
        try {
            statement=con.createStatement();
            resultSet=statement.executeQuery(sql);
            ps=con.prepareStatement(sql1);
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String account=resultSet.getString(2);
                String id1= String.valueOf(id);
                if(id1.equals(account1)){
                    user1.setId(id);
                    user1.setAccount(account);
                    break;
                }else if(account.equals(account1)){
                    user1.setAccount(account);
                    user1.setId(id);
                    break;
                }
            }
            ps.setString(1,user1.getAccount());
            if(user1!=null){
                resultSet=ps.executeQuery();
                String password1=null;
                if(resultSet.next()) {
                password1=resultSet.getString(2);
                    if(password1.equals(password)){
                        user1.setPassword(password);
                    }else{
                        return null;
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                resultSet.close();
                ps.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user1;
    }

    @Override
    public List select_fans(User user)  {
        Connection con=getConnection();
        PreparedStatement st=null;
        String sql="select userid from follows where attention=?";
        ResultSet resultSet=null;
        List list=new ArrayList();
        int id;
        if(user==null){
            return list;
        }
        try {
            st=con.prepareStatement(sql);
            st.setInt(1,user.getId());
            resultSet=st.executeQuery();
            while (resultSet.next()){
                id=resultSet.getInt(1);
                list.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int select_great(User user, int msnum) {
        Connection  con=getConnection();
        String sql="select flag from great where msnum=? and userid=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,msnum);
            ps.setInt(2,user.getId());
            rs=ps.executeQuery();
            if(rs.next()){
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public List<Integer> select_attention(User user) {
        Connection con=getConnection();
        PreparedStatement st=null;
        String sql="select attention from follows where userid=?";
        ResultSet resultSet=null;
        List list=new ArrayList();
        int id;
        try {
            st=con.prepareStatement(sql);
            st.setInt(1,user.getId());
            resultSet=st.executeQuery();
            while (resultSet.next()){
                id=resultSet.getInt(1);
                list.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void insert_fans(User user,int id) {
        Connection con=getConnection();
        String sql="insert into follows values (?,?)";
        String sql1="update users set fans=? where id=?";
        String sql2="update users set attention=? where id=?";
        String sql3="select fans from users where id=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
            ps.setInt(2,id);
            ps.execute();
            ps=con.prepareStatement(sql3);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            int fans=0;
            if(rs.next()) fans=rs.getInt(1);
            ps=con.prepareStatement(sql1);
            ps.setInt(1,fans+1);
            ps.setInt(2,id);
            ps.execute();
            ps=con.prepareStatement(sql2);
            ps.setInt(1,user.getAttention()+1);
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete_fans(User user,int id) {
        Connection con=getConnection();
        String sql="delete from follows where userid=? and attention=?";
        String sql1="update users set fans=? where id=?";
        String sql2="update users set attention=? where id=?";
        String sql3="select fans from users where id=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
            ps.setInt(2,id);
            ps.execute();
            ps=con.prepareStatement(sql3);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            int fans=0;
            if(rs.next()) fans=rs.getInt(1);
            ps=con.prepareStatement(sql1);
            ps.setInt(1,fans-1);
            ps.setInt(2,id);
            ps.execute();
            ps=con.prepareStatement(sql2);
            ps.setInt(1,user.getAttention()-1);
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean select_solo_fans(User user, int id)  {
        Connection con=getConnection();
        ResultSet rs=null;
        PreparedStatement ps=null;
        String sql="select * from follows where userid=? and attention=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
            ps.setInt(2,id);
            rs=ps.executeQuery();
            if(rs.next()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update_head(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="update users set head=? where id=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getHead());
            ps.setInt(2,user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public int insert_home(User user, int msnum) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="insert into house values (?,?)";
        String sql1="update users set house=? where id=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
            ps.setInt(2,msnum);
            ps.execute();
            ps=con.prepareStatement(sql1);
            ps.setInt(1,user.getHouse()+1);
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int delete_home(User user, int msnum) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="delete from house where user_id=? and messagenum=?";
        String sql1="update users set house=? where id=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
            ps.setInt(2,msnum);
            ps.execute();
            ps=con.prepareStatement(sql1);
            ps.setInt(1,user.getHouse()-1);
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    @Override
    public List<Message> select_home(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="select messagenum from house where user_id=?";
        ResultSet resultSet=null;
        List<Message> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
            resultSet=ps.executeQuery();
            while (resultSet.next())
            {
                int msnum=resultSet.getInt(1);
                Message message=new Message();
                message.setNum(msnum);
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int view_home(User user, int msnum) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="select * from house where user_id=? and messagenum=?";
        ResultSet resultSet=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
            ps.setInt(2,msnum);
            resultSet=ps.executeQuery();
            if (resultSet.next()) {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    @Override
    public void update_profile(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="update users set profile=? where id=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getProfile());
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update_sex(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="update users set sex=? where id=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getSex());
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update_hobby(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="update users set hobby=? where id=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getHobby());
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update_city(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="update users set city=? where id=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getCity());
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String select_profile(User user) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="select profile from users where id=?";
        String profile=null;
        ResultSet rs=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
            rs=ps.executeQuery();
            if(rs.next()){
                profile=rs.getString("profile");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return profile;
    }

    @Override
    public List<User> select_user(User user) {
        Connection con=getConnection();
        String sql="select * from users where  nick_name like ? or  true_name like ? or  account like ?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<User> list=new ArrayList<>();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,"%"+user.getNick_name()+"%");
            ps.setString(2,"%"+user.getTrue_name()+"%");
            ps.setString(3,"%"+user.getAccount()+"%");
            rs=ps.executeQuery();
            while (rs.next()){
                User user1=new User();
                user1.setId(rs.getInt(1));
                user1.setTrue_name(rs.getString(2));
                user1.setNick_name(rs.getString(3));
                user1.setAccount(rs.getString(4));
                user1.setSex(rs.getString(5));
                user1.setHobby(rs.getString(6));
                user1.setCity(rs.getString(7));
                user1.setHead(rs.getString(8));
                user1.setFollows(rs.getInt(9));
                user1.setAttention(rs.getInt(10));
                user1.setMessagenum(rs.getInt(11));
                user1.setState(rs.getInt(12));
                user1.setHouse(rs.getInt(13));
                list.add(user1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean update_nick(User user) {
        Connection con=getConnection();
        String sql=" update users set nick_name=? where id=?";
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getNick_name());
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public int update_messagenum(User user) {
        Connection con=getConnection();
        String sql="update users set messagenum=? where id=?";
        PreparedStatement ps=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getMessagenum()+1);
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user.getMessagenum()+1;
    }
}
