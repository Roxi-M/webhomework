package activemq.impl;

import activemq.Consumerdao;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

public class Consumerdaoimpl extends Factory implements Consumerdao {
    public static Consumerdao instance=null;
    public static Consumerdao getInstance(){
        if(instance==null){
            synchronized (Consumerdao.class){
                if(instance==null)
                instance=new Consumerdaoimpl();
            }
        }
        return instance;
    }
    public Consumerdaoimpl(){
        super();
    }
    @Override
    public List<String> recevie() {
       final List<String> list=new ArrayList<>();
        try {
            super.getFactory();
            destination=session.createQueue("test");
            MessageConsumer consumer=session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message!=null){
                        TextMessage textMessage= (TextMessage) message;
                        try {
                             list.add(textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            Thread.sleep(50);
            session.close();
            connection.close();
            consumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void getFactory() throws JMSException {
        factory=new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        connection=factory.createConnection();
        connection.start();
        session=connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
    }
      public List<String> recevie(String queue) {
        final List<String> list=new ArrayList<>();
        try {
            getFactory();
            destination=session.createQueue(queue);
            MessageConsumer consumer=session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message!=null){
                        TextMessage textMessage= (TextMessage) message;
                        try {
                            list.add(textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            Thread.sleep(500);
            session.close();
            connection.close();
            consumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}
