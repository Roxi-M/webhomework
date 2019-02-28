package service;

import been.Message;

public interface Classdao {
    boolean find_movie(String context);

    boolean find_joke(String context);

    boolean find_PE(String context);

    boolean find_social(String context);

    boolean find_shichang(String context);

    boolean find_dongman(String context);
    String json(Message message);
    String select_category(String context);
}
