package ru.otus.kasymbekovPN.HW16M.messageSystem;

import sockets.Entity;
import sockets.SocketHandler;

public interface MsClientService {
    void setSocketHandler(SocketHandler socketHandler);
    boolean createClient(String host, int port, Entity entity);
    MsClient get(String url);
}
