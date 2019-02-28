package service.impl;

import been.Json;
import been.Message;
import been.User;
import dao.Messagedao;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import org.json.JSONArray;
import org.json.JSONObject;
import service.Findmessagedao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindMessagedaoimpl implements Findmessagedao {
    private Messagedao messagedao;
    private Userdao userdao;
    @Override
    public String find_message_home(List<Message> list) {
        messagedao = Messagedaoimpl.getInstance();
        userdao= Userdaoimpl.getInstance();
        JSONArray jsonArray=new JSONArray();
        for (Message message : list) {
            message=messagedao.select_message(message);
            User user =userdao.select_nickname(message.getId());
            message.setNick_name(user.getNick_name());
            JSONObject jsonObject=new JSONObject(message);
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
}
