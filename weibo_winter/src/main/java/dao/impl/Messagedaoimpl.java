package dao.impl;

import been.Message;
import been.User;
import dao.Messagedao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Messagedaoimpl implements Messagedao {
    private static final String DRIVERNAME="com.mysql.cj.jdbc.Driver";
    private static final String DB_URL="jdbc:mysql://localhost:3306/weibo?serverTimezone=UTC";
    private static final String USER="root";
    private static final String PASSWORD="";
    //留言板必须要用单例模式 防止开启多个info 导致数据出现问题
    public static Messagedao instance=null;
    public static Messagedao getInstance(){
        if(instance==null){
            synchronized (Messagedao.class){
                if(instance==null){
                   instance=new Messagedaoimpl();
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
            con= DriverManager.getConnection(DB_URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //发微博
    @Override
    public void insert(been.Message message) {
        Connection con=getConnection();
        String sql="insert into message(userid,info,photo,great,category)values (?,?,?,?,?)";
        PreparedStatement ps=null;
        try {
            String info=URLEncoder.encode(message.getInfo(),"utf-8");
            System.out.println(info);
            ps=con.prepareStatement(sql);
            ps.setInt(1,message.getId());
            ps.setString(2,info);
            ps.setString(3,message.getPhoto());
            ps.setInt(4,0);
            ps.setString(5,message.getCategory());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //搜寻所发微博的个数
    @Override
    public List<Message> select(User user) {
        Connection con=getConnection();
        String sql="select * from message where userid=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<been.Message> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,user.getId());
            rs=ps.executeQuery();
            while (rs.next()){
                int num=rs.getInt(1);
                int userid= rs.getInt(2);
                String info= URLDecoder.decode(rs.getString(3),"utf-8");
                String photo=rs.getString(5);
                int great=rs.getInt(6);
                Message message=new Message(userid,num,info);
                message.setPhoto(photo);
                message.setGreat(great);
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //点赞
    @Override
    public boolean insert_great(User user, Message message) {
        Connection  con=getConnection();
        PreparedStatement ps=null;
        String sql="insert into great(msnum,userid,flag) values (?,?,?)";
        String sql1="update message set great=? where num=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,message.getNum());
            ps.setInt(2,user.getId());
            ps.setInt(3,0);
            ps.execute();
            ps=con.prepareStatement(sql1);
            ps.setInt(1,message.getGreat()+1);
            ps.setInt(2,message.getNum());
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


    //取消点赞
    @Override
    public boolean delete_great(User user, Message message) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        String sql = "delete from great where msnum=? and userid=?";
        String sql1 = "update message set great=? where num=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, message.getNum());
            ps.setInt(2, user.getId());
            ps.execute();
            ps = con.prepareStatement(sql1);
            ps.setInt(1, message.getGreat() - 1);
            ps.setInt(2, message.getNum());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    //搜索单条微博
    @Override
    public Message select_message(Message message) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        String sql="select * from message where  num=? ";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,message.getNum());
            resultSet=ps.executeQuery();
            if(resultSet.next()){
                message.setNum(resultSet.getInt(1));
                message.setId(resultSet.getInt(2));
                message.setInfo(URLDecoder.decode(resultSet.getString(3),"utf-8"));
                message.setPhoto(resultSet.getString(5));
                message.setGreat(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                ps.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    @Override
    public Message select_message(String info, String user_id) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        Message message=new Message();
        String sql="select * from message where userid=? and info=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(user_id));
            ps.setString(2, URLEncoder.encode(info,"utf-8"));
            resultSet=ps.executeQuery();
            if(resultSet.next()){
                message.setNum(resultSet.getInt(1));
                message.setId(resultSet.getInt(2));
                message.setInfo(URLDecoder.decode(resultSet.getString(3),"utf-8"));
                message.setPhoto(resultSet.getString(5));
                message.setGreat(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                ps.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    @Override
    public List<Message> select_list(Message message) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="select * from reply where msnum=?";
        ResultSet resultSet=null;
        List<Message> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,message.getNum());
            resultSet=ps.executeQuery();
            while (resultSet.next()){
                Message message1=new Message();
                message1.setNum(resultSet.getInt(1));
                message1.setInfo(URLDecoder.decode(resultSet.getString(5),("utf-8")));
                message1.setFather(resultSet.getInt(3));
                message1.setId(resultSet.getInt(4));
                list.add(message1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
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
    public void insert_reply(User user,Message message) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="insert into reply(msnum,userid,info) values (?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,message.getFather());
            ps.setInt(2,user.getId());
            ps.setString(3,URLEncoder.encode(message.getInfo(),"utf-8"));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Message> select_list_reply(Message message) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="select * from reply where childnum=?";
        ResultSet resultSet=null;
        List<Message> messageList=new ArrayList<>();
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,message.getNum());
            resultSet=ps.executeQuery();
            while (resultSet.next()){
                Message message1=new Message();
                message1.setId(resultSet.getInt(4));
                message1.setNum(resultSet.getInt(1));
                message1.setInfo(URLDecoder.decode(resultSet.getString(5),"utf-8"));
                message1.setFather(resultSet.getInt(2));
                messageList.add(message1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return messageList;
    }

    @Override
    public List<Message> select_list_message(Message message) {
        Connection con=getConnection();
        String sql="select * from message where info like ?";
        ResultSet resultSet=null;
        PreparedStatement ps=null;
        List<Message> list=new ArrayList<>();
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,"%"+URLEncoder.encode(message.getInfo(),"utf-8")+"%");
            resultSet=ps.executeQuery();
            while (resultSet.next()){
                Message message1=new Message();
                message1.setId(resultSet.getInt(1));
                message1.setNum(resultSet.getInt(2));
                message1.setInfo(URLDecoder.decode(resultSet.getString(3),"utf-8"));
                message1.setPhoto(resultSet.getString(4));
                message1.setGreat(resultSet.getInt(5));
                list.add(message1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert_reply_reply(User user, Message message) {
        Connection con=getConnection();
        PreparedStatement ps=null;
        String sql="insert into reply(childnum,userid,info,msnum) values (?,?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,message.getFather());
            ps.setInt(2,user.getId());
            ps.setString(3,URLEncoder.encode(message.getInfo(),"utf-8"));
            ps.setInt(4,0);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete_message(User user, Message message) {
        Connection con=getConnection();
        String sql="delete from message where num=?";
        PreparedStatement ps=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,message.getNum());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete_reply(Message message) {
        Connection con=getConnection();
        String sql="delete from reply where rpnum=?";
        PreparedStatement ps=null;
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,message.getNum());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Message> select_class(Message message) {
        Connection con=getConnection();
        ResultSet rs=null;
        PreparedStatement ps=null;
        List<Message> list=new ArrayList<>();
        String sql="select * from message where category=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,message.getCategory());
            rs=ps.executeQuery();
            while (rs.next()){
                message.setNum(rs.getInt(1));
                message.setId(rs.getInt(2));
                message.setInfo(URLDecoder.decode(rs.getString(3),"utf-8"));
                message.setCategory(rs.getString(4));
                message.setPhoto(rs.getString(5));
                message.setGreat(rs.getInt(6));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Message> select_alloff() {
        Connection con=getConnection();
        ResultSet rs=null;
        Statement st=null;
        List<Message> list=new ArrayList<>();
        String sql="select * from message ";
        try {
            st=con.createStatement();
            rs=st.executeQuery(sql);
            while (rs.next()){
                Message message=new Message();
                message.setNum(rs.getInt(1));
                message.setId(rs.getInt(2));
                message.setInfo(URLDecoder.decode(rs.getString(3),"utf-8"));
                message.setCategory(rs.getString(4));
                message.setPhoto(rs.getString(5));
                message.setGreat(rs.getInt(6));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                con.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
