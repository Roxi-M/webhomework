package service.impl;

import been.Message;
import been.User;
import dao.Messagedao;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import org.json.JSONArray;
import org.json.JSONObject;
import service.UserService;

import java.util.List;

public class UserServiceimpl implements UserService {
    private Userdao userdao;
    private Messagedao messagedao;
    @Override
    public String tojson(User user) {
        JSONObject jsonObject=new JSONObject(user);
        String json=jsonObject.toString();
        return json;
    }

    @Override
    public String select_user(List list) {
        userdao = Userdaoimpl.getInstance();
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            User user = new User();  //创建一个user对象
            user.setId((int) list.get(i)); //找userid
            user = userdao.select_all(user);//搜索这个 id 用户所有信息
            JSONObject jsonObject=new JSONObject(user);
            jsonArray.put(jsonObject);
        }
        String json=jsonArray.toString();
        return json;
    }

    @Override
    public String select_message(User user) {
        messagedao= Messagedaoimpl.getInstance();
        List<Message> list=messagedao.select(user);
        JSONArray jsonArray=new JSONArray();
        for(int i=list.size()-1;i>=0;i--){
            list.get(i).setNick_name(user.getNick_name());
            JSONObject jsonObject=new JSONObject(list.get(i));
            jsonArray.put(jsonObject);
        }
        String json=jsonArray.toString();
        return json;
    }

}
