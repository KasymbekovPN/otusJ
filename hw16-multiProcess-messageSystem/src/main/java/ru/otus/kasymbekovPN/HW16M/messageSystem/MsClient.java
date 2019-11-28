package ru.otus.kasymbekovPN.HW16M.messageSystem;

import sockets.ReqRespType;

public interface MsClient {
    void addHandler(ReqRespType type, ReqRespHandler handler);
    boolean sendMessage(Message message);
    void handle(Message message);
    String getUrl();
    <T> Message produceMessage(String toUrl, T data, ReqRespType type);
}