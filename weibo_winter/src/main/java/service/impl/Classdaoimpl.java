package service.impl;

import been.Message;
import been.User;
import dao.Messagedao;
import dao.Userdao;
import dao.impl.Messagedaoimpl;
import dao.impl.Userdaoimpl;
import org.json.JSONArray;
import org.json.JSONObject;
import service.Classdao;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Classdaoimpl implements Classdao {
    private String movie="^#电影#";
    private String dongman="^#动漫#";
    private String joke="^#搞笑#";
    private String PE="^#体育#";
    private String shishang="^#时尚#";
    private String social="^#社会#";
    private Messagedao messagedao;
    private Userdao userdao;
    @Override
    public boolean find_movie(String context) {
        Pattern pattern=Pattern.compile(movie);
        Matcher matcher=pattern.matcher(context);
        if(matcher.find()){
            return true;
        }
        return false;
    }


    @Override
    public boolean find_joke(String context) {
        Pattern pattern=Pattern.compile(joke);
        Matcher matcher=pattern.matcher(context);
        if(matcher.find()){
            return true;
        }
        return false;
    }



    @Override
    public boolean find_PE(String context) {
        Pattern pattern=Pattern.compile(PE);
        Matcher matcher=pattern.matcher(context);
        if(matcher.find()){
            return true;
        }
        return false;
    }


    @Override
    public boolean find_social(String context) {
        Pattern pattern=Pattern.compile(social);
        Matcher matcher=pattern.matcher(context);
        if(matcher.find()){
            return true;
        }
        return false;
    }


    @Override
    public boolean find_shichang(String context) {
        Pattern pattern=Pattern.compile(shishang);
        Matcher matcher=pattern.matcher(context);
        if(matcher.find()){
            return true;
        }
        return false;
    }


    @Override
    public boolean find_dongman(String context) {
        Pattern pattern=Pattern.compile(dongman);
        Matcher matcher=pattern.matcher(context);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    @Override
    public String json(Message message) {
        messagedao= Messagedaoimpl.getInstance();
        userdao=Userdaoimpl.getInstance();
        List<Message> messageList=messagedao.select_class(message);
        JSONArray jsonArray=new JSONArray();
        for(int i=messageList.size()-1;i>=0;i--){
            User user=userdao.select_nickname(messageList.get(i).getId());
            messageList.get(i).setNick_name(user.getNick_name());
            messageList.get(i).setPhoto_head(user.getHead());
            JSONObject jsonObject=new JSONObject(messageList.get(i));
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @Override
    public String select_category(String context) {
        if(find_dongman(context)){
            return "dongman";
        }else if(find_joke(context)){
            return "gaoxiao";
        }else if(find_movie(context)){
            return "dianying";
        }else if(find_social(context)){
            return "shehui";
        }else if(find_PE(context)){
            return "tiyu";
        }else if(find_shichang(context)){
            return "shichang";
        }
        return "wu";
    }
}
