package activemq;

public interface Producerdao {
     void send(String msg);
     void send_queue(String msg,String topic);
}
