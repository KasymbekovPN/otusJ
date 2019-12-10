package ru.otus.kasymbekovPN.HW16M.messageSystem;

import sockets.ReqRespType;

public interface MsClient {
    void addHandler(ReqRespType type, MSMessageHandler handler);
    boolean sendMessage(Message message);
    void handle(Message message);
    String getUrl();
//    <T> Message produceMessage(String fromUrl, T data, ReqRespType type);
    //<
    <T> Message produceMessage(String toUrl, T data, ReqRespType type);
}
