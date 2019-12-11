package ru.otus.kasymbekovPN.HW16M.messageSystem;

import sockets.SocketHandler;

@FunctionalInterface
public interface MsClientCreator {
    abstract public MsClient create(String url, SocketHandler socketHandler, MessageSystem messageSystem);
}
