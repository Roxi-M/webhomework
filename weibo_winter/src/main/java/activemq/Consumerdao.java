package activemq;

import java.util.List;

public interface Consumerdao {
    List<String> recevie();
    List<String> recevie(String queue);
}
