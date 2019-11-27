package ru.otus.kasymbekovPN.HW16M.messageSystem;

import sockets.ReqRespType;

public interface MsClient {
    void addHandler(ReqRespType type, ReqRespHandler handler);
    boolean sendMessage(Message message);
    void handle(Message message);
    String getHost();
    int getPort();
    <T> Message produceMessage(String toHost, int toPort, T data, ReqRespType type);
}
