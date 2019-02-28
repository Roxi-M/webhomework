package activemq.impl;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Factory {
    ConnectionFactory factory;
    Connection connection;
    Session session;
    Destination destination;
    protected void getFactory() throws JMSException {
         factory=new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
         connection=factory.createConnection();
         connection.start();
         session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
    }
}
