package ru.otus.kasymbekovPN.HW16M.messageSystem.client.creator;

import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import sockets.SocketHandler;

@FunctionalInterface
public interface MsClientCreator {
    abstract public MsClient create(String url, SocketHandler socketHandler, MessageSystem messageSystem);
}
