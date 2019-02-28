package activemq.impl;

import activemq.Consumerdao;
import activemq.Producerdao;
import activemq.impl.Factory;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;


public class ActiveHot extends Factory implements Consumerdao , Producerdao {
    @Override
    public List<String> recevie() {
        try {
            List<String> list=new ArrayList<>();
            getFactory();
            session=connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
            destination=session.createQueue("Hotweibo");
            MessageConsumer consumer=session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message!=null) {
                        if (message instanceof TextMessage) {

                        }
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> recevie(String queue) {

        return null;
    }
    @Override
    public void getFactory() throws JMSException {
        factory=new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        connection=factory.createConnection();
        connection.start();
    }
    @Override
    public void send(String msg) {
        try {
            getFactory();
            Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination=session.createQueue("Hotweibo");
            MessageProducer producer=session.createProducer(destination);
            producer.setTimeToLive(1000*60*60*24);
            TextMessage message=session.createTextMessage(msg);
            producer.send(message);
            connection.close();
            session.close();
            producer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send_queue(String msg, String topic) {

    }
}
