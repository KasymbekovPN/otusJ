package ru.otus.kasymbekovPN.HW16M.messageSystem.client.service;

import entity.Entity;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import sockets.SocketHandler;

public interface MsClientService {
    boolean createClient(String host, int port, Entity entity, MessageSystem messageSystem);
    void deleteClient(String url);
    void setSocketHandler(SocketHandler socketHandler);
    MsClient get(String url);
}
