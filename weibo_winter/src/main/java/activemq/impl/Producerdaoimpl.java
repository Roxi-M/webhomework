package activemq.impl;

import activemq.Producerdao;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

public class Producerdaoimpl extends Factory implements Producerdao {
    public static Producerdao instance;
    public static Producerdao getInstance(){
        if(instance==null){
            synchronized (Producerdao.class){
                if(instance==null){
                    instance=new Producerdaoimpl();
                }
            }
        }
        return instance;
    }
    public Producerdaoimpl(){
        super();
    }

    public void send_queue(String msg,String queue) {
        try {
            super.getFactory();
            destination=session.createQueue(queue);
            MessageProducer producer=session.createProducer(destination);
            producer.setTimeToLive(60*60*24*1000);
            TextMessage message=session.createTextMessage(msg);
            producer.send(message);
            super.session.close();
            producer.close();
            super.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(String msg) {
        try {
            super.getFactory();
            destination=session.createQueue("test");
            MessageProducer producer=session.createProducer(destination);
            TextMessage message=session.createTextMessage(msg);
            producer.send(message);
            super.session.close();
            producer.close();
            super.connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
